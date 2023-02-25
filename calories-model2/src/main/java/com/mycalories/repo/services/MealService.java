package com.mycalories.repo.services;

import com.mycalories.model2.meal.Meal;
import com.mycalories.model2.meal.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;

    public List<Meal> findAll(){
        return mealRepository.findAll();
    }

    public Meal createTotal(Meal meal) {
        return mealRepository.save(meal);
    }

    public List<Meal> getAllTotalsByDate(String date) {
        return mealRepository.findByDate(date);
    }

    public void deleteTotal(Meal meal) {
        mealRepository.delete(meal);
    }

}
