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
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name="DAY")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Meal breakfast;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Meal lunch;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Meal dinner;

    public static Day createDay(DayOfWeek dayOfWeek) {
        return new Day(null, dayOfWeek, null, null, null );
    }

}
