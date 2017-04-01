package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.group11501.vitise.util.AuthForm;

@Controller
public class SignInController {
    @RequestMapping("/sign_in")
    public String getSignIn(@RequestParam(value = "error", required = false) Boolean error,
                            Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        model.addAttribute("authForm", new AuthForm());
        return "sign_in";
    }
}
