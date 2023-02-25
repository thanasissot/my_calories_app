package com.mycalories.caloriesrest;

import com.mycalories.caloriesrest.service.FoodService;
import com.mycalories.caloriesrest.service.MealService;
import com.mycalories.model2.food.Food;
import com.mycalories.model2.meal.Meal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
@Log4j2
public class CreateData {
    private final FoodService foodService;
    private final MealService mealService;


    public List<Food> createFoods() {
        Random random = new Random();
        List<Food> foodList = new ArrayList<>();
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(1);
        formatter.setMinimumFractionDigits(1);
        formatter.setRoundingMode(RoundingMode.HALF_UP);

        for (int i = 1; i < 6; i++) {
            String name  = String.join("", "food", String.valueOf(i));
            Food food;
            if ((food = foodService.findByName(name)) == null) {
                food = new Food(name, random.nextInt(500) + 1,
                        Float.parseFloat(formatter.format(random.nextFloat(100)).replace(",", ".")),
                        Float.parseFloat(formatter.format(random.nextFloat(100)).replace(",", ".")),
                        Float.parseFloat(formatter.format(random.nextFloat(100)).replace(",", ".")));
                food = foodService.createFood(food);
            }
            foodList.add(food);

        }

        return foodList;
    }

    public void createTotals(List<Food> foodList) {
        Random random = new Random();
        LocalDateTime ldt = LocalDateTime.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);
        if (!mealService.getAllTotalsByDate(date).isEmpty())
            return;

        for (Food food : foodList) {
            int rand = random.nextInt(500);
            Meal total = new Meal(null, date, rand, food.getName(), (food.getCalories() * rand) * 0.01);
            total = mealService.createTotal(total);
        }
    }

    @PostConstruct
    public void run() {
        List<Food> foodlist = createFoods();
        createTotals(foodlist);
    }
}
