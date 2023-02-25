package com.mycalories.caloriesrest.controller;


import com.mycalories.caloriesrest.service.MealService;
import com.mycalories.model2.meal.Meal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal")
@RequiredArgsConstructor
@Log4j2
public class MealController {
    private final MealService mealService;

    @GetMapping
    ResponseEntity<List<Meal>> getAllTotals() {
        return new ResponseEntity<>(mealService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    ResponseEntity<List<Meal>> getAllTotalsByDate(@PathVariable String date) {
        return new ResponseEntity<>(mealService.getAllTotalsByDate(date), HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<Meal> createTotal(@Validated @RequestBody Meal total) {
        return new ResponseEntity<>(mealService.createTotal(total), HttpStatus.OK);
    }

    @PostMapping("/delete")
    ResponseEntity<HttpStatus> deleteTotal(@Validated @RequestBody Meal total) {
        try {
            mealService.deleteTotal(total);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}