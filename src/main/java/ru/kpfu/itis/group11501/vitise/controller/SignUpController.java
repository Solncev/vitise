package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.group11501.vitise.model.Group;
import ru.kpfu.itis.group11501.vitise.model.Student;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;
import ru.kpfu.itis.group11501.vitise.service.GroupService;
import ru.kpfu.itis.group11501.vitise.service.StatusService;
import ru.kpfu.itis.group11501.vitise.service.StudentService;
import ru.kpfu.itis.group11501.vitise.service.UserService;
import ru.kpfu.itis.group11501.vitise.util.UserForm;
import ru.kpfu.itis.group11501.vitise.util.UserFormToUserTransformer;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Controller
public class SignUpController {

    private final Function<UserForm, User> userFormToUserTransformer;
    private final UserService userService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final StatusService statusService;
    private List<StatusName> statusList;

    @Autowired
    public SignUpController(UserService userService, StudentService studentService, GroupService groupService, StatusService statusService) {
        this.userService = userService;
        this.studentService = studentService;
        this.groupService = groupService;
        this.statusService = statusService;
        this.userFormToUserTransformer = new UserFormToUserTransformer();
        this.statusList = new ArrayList<>();
        statusList.add(StatusName.TEACHER);
        statusList.add(StatusName.RESEARCHER);
        statusList.add(StatusName.ASSISTANT);
    }

    @RequestMapping(value = "/sign_up/stud")
    public String signUpStudentPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("role", "stud");
        return "sign_up";
    }

    @RequestMapping(value = "/sign_up/worker")
    public String signUpWorkerPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("statuses", statusList);
        model.addAttribute("role", "worker");
        return "sign_up";
    }

    @RequestMapping(value = "/sign_up/stud", method = RequestMethod.POST)
    public String signUpStudent(@ModelAttribute("userForm") @Valid UserForm userForm,
                                BindingResult bindingResult, Model model,
                                @RequestParam(name = "group", required = false) String groupId) {
        if (!bindingResult.hasErrors()) {
            userService.add(userFormToUserTransformer.apply(userForm));
            User user = userService.getUser(userForm.getEmail());
            Group group = groupService.getGroup(Long.valueOf(groupId));
            Student student = new Student();
            student.setGroup(group);
            student.setStudent(user);
            studentService.add(student);
            statusService.addUsersStatus(user, StatusName.STUDENT);
            return "redirect:/sign_in";
        }
        model.addAttribute("role", "stud");
        return "sign_up";
    }

    @RequestMapping(value = "/sign_up/worker", method = RequestMethod.POST)
    public String signUpWorker(@ModelAttribute("userForm") @Valid UserForm userForm,
                               BindingResult bindingResult, Model model,
                               @RequestParam(name = "status") StatusName status) {
        if (!bindingResult.hasErrors()) {
            userService.add(userFormToUserTransformer.apply(userForm));
            User user = userService.getUser(userForm.getEmail());
            statusService.addUsersStatus(user, StatusName.WORKER);
            statusService.addUsersStatus(user, status);
            return "redirect:/sign_in";
        }
        model.addAttribute("role", "worker");
        model.addAttribute("statuses", statusList);
        return "sign_up";
    }


    @RequestMapping(value = "/email_check")
    @ResponseBody
    public String emailCheck(@RequestParam(name = "email", required = false) String email) {
        if (userService.getUser(email) != null)
            return "This e-mail already exists. ";
        return "Available e-mail";
    }

    @RequestMapping(value = "/get_groups")
    @ResponseBody
    public Map<Long, String> getGroups(@RequestParam(name = "course_number", required = false) String course) {
        return groupService.getGroupsByCourse(Integer.parseInt(course));
    }
}
