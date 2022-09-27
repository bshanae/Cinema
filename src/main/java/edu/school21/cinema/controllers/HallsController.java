package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Hall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin/panel/halls")
@Controller
public class HallsController {
    private static final List<Hall> halls;

    static {
        halls = new ArrayList<Hall>();
        halls.add(new Hall(1, 1));
    }

    @GetMapping()
    public String getHalls(Model model) {
        model.addAttribute("halls", halls);
        return "halls";
    }

    @PostMapping("/add")
    public String addHall(@ModelAttribute("numberOfSeats") int numberOfSeats) {
        halls.add(new Hall(1, numberOfSeats));
        return "redirect:/admin/panel/halls";
    }
}
