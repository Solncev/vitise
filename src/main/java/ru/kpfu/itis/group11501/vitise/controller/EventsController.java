package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.group11501.vitise.model.Event;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.service.EventService;
import ru.kpfu.itis.group11501.vitise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Author: Svintenok Kate
 * Date: 22.03.2017
 * Group: 11-501
 * Project: vITISe
 */

@Controller
public class EventsController {

    private final EventService eventService;
    private final UserService userService;
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public EventsController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @RequestMapping(value = "/events")
    public String publicEventsPage(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("events", eventService.getEvents(currentUser));
        model.addAttribute("currentUser", currentUser);
        return "events";
    }

    @RequestMapping(value = "/user/{id}/events")
    public String userEventsPage(@PathVariable(value = "id") Long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(id);
        if (user == null)
            return "handle404";

        model.addAttribute("user", user);
        model.addAttribute("current_user", currentUser);
        model.addAttribute("events", eventService.getUserEvents(user, currentUser));
        return "user_events";

    }

    @RequestMapping(value = "/events/subscribe", method = RequestMethod.POST)
    public String eventSubscribe(@RequestParam(name = "event_id") Long eventId, HttpServletRequest request) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        eventService.changeSubscribeStatus(eventService.getEvent(eventId), currentUser);
        return "redirect:" + request.getHeader("Referer");
    }


    @RequestMapping(value = "/events/remove", method = RequestMethod.POST)
    public String eventRemove(@RequestParam(name = "event_id") Long eventId, HttpServletRequest request) {
        eventService.removeEvent(eventService.getEvent(eventId));
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/events/create", method = RequestMethod.POST)
    public String createEvent(@RequestParam Map<String, String> allRequestParams, HttpServletRequest request) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Event event = new Event();
        event.setPublic(false);
        event.setAuthor(currentUser);
        event.setText(allRequestParams.get("text"));

        event.setName("");
        if (request.isUserInRole("ROLE_WORKER") || request.isUserInRole("ROLE_DEANERY")) {
            event.setPublic(true);
        }

        try {
            event.setEventDate(format.parse(allRequestParams.get("eventDate")));
            eventService.add(event);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (request.isUserInRole("ROLE_WORKER") || request.isUserInRole("ROLE_DEANERY"))
            return "redirect:/news";
        else
            return "redirect:/user/" + currentUser.getId() + "/events";
    }
}

