package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.vitise.model.Event;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * Author: Svintenok Kate
 * Date: 22.03.2017
 * Group: 11-501
 * Project: vITISe
 */
@Controller
public class EventCreateController {

    private final Pattern pattern = Pattern.compile("^\\S+$");
    private final EventService eventService;

    public EventCreateController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/events/create", method = RequestMethod.GET)
    public String eventCreatePage(Model model) {
        model.addAttribute("event", new Event());
        return "event_create";
    }

    @RequestMapping(value = "/events/create", method = RequestMethod.POST)
    public String createEvent(@ModelAttribute(name = "event") Event event, HttpServletRequest request) {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        event.setPublic(false);
        event.setAuthor(currentUser);
        if (request.isUserInRole("ROLE_WORKER") || request.isUserInRole("ROLE_DEANERY")) {
            event.setPublic(true);
        }

        if (pattern.matcher(event.getText()).matches()) {
            eventService.add(event);
        }

        if (request.isUserInRole("ROLE_WORKER") || request.isUserInRole("ROLE_DEANERY"))
            return "redirect:/events";
        else
            return "redirect:/user/" + currentUser.getId() + "/events";
    }
}