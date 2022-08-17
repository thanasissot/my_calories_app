package com.mycalories.caloriesrest.controller;

import com.mycalories.caloriesrest.service.FoodService;
import com.mycalories.model2.food.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    ResponseEntity<List<Food>> getAllFoods() {
        return new ResponseEntity<>(foodService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Food> createFood(@Validated @RequestBody Food food) {
        return new ResponseEntity<>(foodService.createFood(food), HttpStatus.OK);
    }

}