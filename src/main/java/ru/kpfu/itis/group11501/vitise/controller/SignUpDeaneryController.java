package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;
import ru.kpfu.itis.group11501.vitise.service.StatusService;
import ru.kpfu.itis.group11501.vitise.service.UserService;
import ru.kpfu.itis.group11501.vitise.util.UserForm;
import ru.kpfu.itis.group11501.vitise.util.UserFormToUserTransformer;

import javax.validation.Valid;
import java.util.function.Function;

/**
 * Created by Наталья on 08.04.2017.
 */
@Controller
@RequestMapping("/admin")
public class SignUpDeaneryController {

    private final UserService userService;
    private final StatusService statusService;
    private final Function<UserForm, User> userFormToUserTransformer;

    @Autowired
    public SignUpDeaneryController(UserService userService, StatusService statusService) {
        this.userService = userService;
        this.statusService = statusService;
        this.userFormToUserTransformer = new UserFormToUserTransformer();
    }

    @RequestMapping(value = "/add_deanery", method = RequestMethod.GET)
    public String signUpPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "add_deanery";
    }

    @RequestMapping(value = "/add_deanery", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("userForm") @Valid UserForm userForm,
                         BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            userService.add(userFormToUserTransformer.apply(userForm));
            User user = userService.getUser(userForm.getEmail());
            statusService.addUsersStatus(user, StatusName.DEANERY);
            return "redirect:/profile";
        }
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "add_deanery";
    }
}
