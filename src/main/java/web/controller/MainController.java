package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String startIndex(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());

        return "index";
    }

    @GetMapping(value = "remove")
    public String removeUser(@RequestParam Integer id) {
        userService.remove(id);
        return "redirect:/";
    }

    @GetMapping(value = "edit")
    public String editUser(@RequestParam Integer id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "editUser";
    }

    @PostMapping(value = "edit")
    public String updateUser(@RequestParam("id") Integer id,
                            @RequestParam("name") String name,
                            @RequestParam("surname") String surname,
                            @RequestParam("department") String department,
                            @RequestParam("salary") Integer salary) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setDepartment(department);
        user.setSalary(salary);
        userService.update(user);
        return "redirect:/";
    }

    @GetMapping(value = "new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping(value = "new")
    public String createUser(@RequestParam("name") String name,
                            @RequestParam("surname") String surname,
                            @RequestParam("department") String department,
                            @RequestParam("salary") Integer salary) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setDepartment(department);
        user.setSalary(salary);
        userService.create(user);
        return "redirect:/";
    }

}