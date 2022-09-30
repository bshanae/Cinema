package edu.school21.cinema.controllers;

import edu.school21.cinema.services.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/panel/halls")
@Controller
public class HallsController {
    private final HallService hallService;

    public HallsController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping()
    public String getHalls(Model model) {
        model.addAttribute("halls", hallService.getHalls());
        return "halls";
    }

    @PostMapping("/add")
    public String addHall(@ModelAttribute("numberOfSeats") int numberOfSeats) {
        hallService.addHall(numberOfSeats);
        return "redirect:/admin/panel/halls";
    }
}
