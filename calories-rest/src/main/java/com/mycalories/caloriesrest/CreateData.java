package com.mycalories.caloriesrest;

import com.mycalories.caloriesrest.service.FoodService;
import com.mycalories.caloriesrest.service.TotalService;
import com.mycalories.model2.food.Food;
import com.mycalories.model2.total.Total;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CreateData {
    private final FoodService foodService;
    private final TotalService totalService;
    private final Random random = new Random();

    private final List<Food> foodList = new ArrayList<>();

    public void createFoods() {
        for (int i = 1; i < 6; i++) {
            String name  = String.join("", "food", String.valueOf(i));
            Food food;
            if ((food = foodService.findByName(name)) == null) {
                food = new Food(name, random.nextInt(500) + 1, random.nextFloat(100), random.nextFloat(100), random.nextFloat(100));
                foodService.createFood(food);
            } else {
                foodList.add(food);
            }
        }
    }

    public void createTotals() {
        LocalDateTime ldt = LocalDateTime.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
        for (Food food : foodList) {
            Total total = new Total((Long) null, date, random.nextInt(500), food);
            totalService.createTotal(total);
        }
    }

    @PostConstruct
    public void run() {
        createFoods();
        createTotals();
    }
}
