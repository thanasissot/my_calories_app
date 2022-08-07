package com.mycalories.caloriesrest.controller;

import com.mycalories.caloriesrest.service.FoodService;
import com.mycalories.caloriesrest.service.TotalService;
import com.mycalories.model.model.Food;
import com.mycalories.model.model.Total;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/total")
@RequiredArgsConstructor
public class TotalController {
    private final TotalService totalService;
    private final FoodService foodService;

    @GetMapping
    ResponseEntity<List<Total>> getAllTotals() {
        return new ResponseEntity<>(totalService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Total> createTotal(@Validated @RequestBody Total total) {
        return new ResponseEntity<>(totalService.createTotal(total), HttpStatus.OK);
    }

}