package com.myla.mealplanner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meal-planner")
@RequiredArgsConstructor
public class DayController {

    private final DayRepository dayRepository;

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("days", dayRepository.findAll());

        return "index";

    }


}
