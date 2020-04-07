package com.myla.mealplanner;

import com.myla.mealplanner.entity.Day;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/meal-planner", method = RequestMethod.GET)
@RequiredArgsConstructor
public class DayController {

    private final DayRepository dayRepository;

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("days", dayRepository.findAll());

        return "index";

    }

    @PostMapping("/edit/{id}")
    public String add(@Valid Day day, Model model, @PathVariable(name = "id") Long id) {

        model.addAttribute("day", dayRepository.findById(id));

        return "edit";
    }


}
