package com.myla.mealplanner.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Valid
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Meal breakfast;

    @Valid
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Meal lunch;

    @Valid
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Meal dinner;

    public static Day createDay(DayOfWeek dayOfWeek) {
        return new Day(null, dayOfWeek, null, null, null );
    }

    public String getMealName(Meal meal) {
        return Optional.ofNullable(meal)
                .map(Meal::getName)
                .orElse("");
    }

}
