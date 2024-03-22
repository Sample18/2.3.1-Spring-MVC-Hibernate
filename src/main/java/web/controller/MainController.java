package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Employe;
import web.service.EmployeService;

@Controller
public class MainController {

    private final EmployeService employeService;

    @Autowired
    public MainController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping(value = "/")
    public String printWelcome(Model model) {
        model.addAttribute("employeList", employeService.getAllEmploye());

        return "index";
    }

    @GetMapping(value = "remove")
    public String removeEmp(@RequestParam Integer id) {
        employeService.remove(id);
        return "redirect:/";
    }

    @GetMapping(value = "edit")
    public String editEmp(@RequestParam Integer id, Model model) {
        model.addAttribute("emp", employeService.findById(id));
        return "editEmploye";
    }

    @PostMapping(value = "edit")
    public String updateEmp(@RequestParam("id") Integer id,
                            @RequestParam("name") String name,
                            @RequestParam("surname") String surname,
                            @RequestParam("department") String department,
                            @RequestParam("salary") Integer salary) {
        Employe employe = new Employe();
        employe.setId(id);
        employe.setName(name);
        employe.setSurname(surname);
        employe.setDepartment(department);
        employe.setSalary(salary);
        employeService.update(employe);
        return "redirect:/";
    }

    @GetMapping(value = "new")
    public String newEmp(Model model) {
        model.addAttribute("emp", new Employe());
        return "addEmploye";
    }

    @PostMapping(value = "new")
    public String createEmp(@RequestParam("name") String name,
                            @RequestParam("surname") String surname,
                            @RequestParam("department") String department,
                            @RequestParam("salary") Integer salary) {
        Employe employe = new Employe();
        employe.setName(name);
        employe.setSurname(surname);
        employe.setDepartment(department);
        employe.setSalary(salary);
        employeService.create(employe);
        return "redirect:/";
    }

}