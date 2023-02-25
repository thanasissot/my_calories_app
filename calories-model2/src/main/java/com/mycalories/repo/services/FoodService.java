package com.mycalories.repo.services;

import com.mycalories.model2.food.Food;
import com.mycalories.model2.food.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public List<Food> findAll(){
        return foodRepository.findAll();
    }

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public Food findByName(String name){
        return foodRepository.findByName(name).orElse(null);
    }
}
