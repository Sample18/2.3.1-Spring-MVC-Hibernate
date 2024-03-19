package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.CarDAO;

@Controller
@RequestMapping("/cars")
public class CarsController {

    private final CarDAO carDAO;

    public CarsController(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @GetMapping()
    public String indexCars(@RequestParam(value = "count", required = false) Integer count, Model model) {
        if(count != null && count > 0) {
            if(count < 6) {
                model.addAttribute("cars", carDAO.getCars(count));
            } else {
                model.addAttribute("cars", carDAO.getAllCars());
            }
        } else {
            model.addAttribute("cars", carDAO.getAllCars());
        }
        return "cars";
    }
}
