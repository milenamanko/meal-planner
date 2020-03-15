package com.myla.mealplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToMany
    private List<Meal> mealList;

    public static Day createDay(DayOfWeek dayOfWeek) {
        return new Day(null, dayOfWeek, new ArrayList<>());
    }

    public String getBreakfastName() {
        return mealList.stream()
                .filter(meal -> MealType.BREAKFAST.equals(meal.getType()))
                .findAny()
                .map(Meal::getName)
                .orElse("");
    }

    public String getLunchName() {
        return mealList.stream()
                .filter(meal -> MealType.LUNCH.equals(meal.getType()))
                .findAny()
                .map(Meal::getName)
                .orElse("");
    }

    public String getDinnerName() {
        return mealList.stream()
                .filter(meal -> MealType.DINNER.equals(meal.getType()))
                .findAny()
                .map(Meal::getName)
                .orElse("");
    }
}
