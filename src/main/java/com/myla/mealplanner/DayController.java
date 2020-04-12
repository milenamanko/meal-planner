package com.myla.mealplanner;

import com.myla.mealplanner.entity.Day;
import com.myla.mealplanner.entity.Meal;
import com.myla.mealplanner.entity.MealType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

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
        Optional<Day> optionalDay = dayRepository.findById(id);

        if (optionalDay.isPresent()) {
            model.addAttribute("day", optionalDay.get());
        } else {
            throw new IllegalArgumentException("invalid Day id");
        }

        return "edit";
    }

    @PostMapping("/save/{id}")
    public String saveEdit(@Valid Day day, BindingResult bindingResult, @PathVariable("id") Long id, Model model) {
        day.setId(id);

        if (bindingResult.hasErrors()) {
            return "edit";
        }

        day.getBreakfast().setMealType(MealType.BREAKFAST);
        day.getLunch().setMealType(MealType.LUNCH);
        day.getDinner().setMealType(MealType.DINNER);

        dayRepository.save(day);

        model.addAttribute("days", dayRepository.findAll());

        return "index";
    }


}
