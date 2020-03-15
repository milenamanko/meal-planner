package com.myla.mealplanner;

import com.myla.mealplanner.entity.Day;
import com.myla.mealplanner.entity.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final DayRepository dayRepository;

    @Override
    public void run(ApplicationArguments args) {
        List<Day> days = Arrays.stream(DayOfWeek.values())
                .map(Day::createDay)
                .collect(Collectors.toList());

        dayRepository.saveAll(days);
    }
}
