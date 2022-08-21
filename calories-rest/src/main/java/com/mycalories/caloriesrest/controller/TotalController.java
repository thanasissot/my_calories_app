package com.mycalories.caloriesrest.controller;


import com.mycalories.caloriesrest.service.FoodService;
import com.mycalories.caloriesrest.service.TotalService;
import com.mycalories.model2.total.Total;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/total")
@RequiredArgsConstructor
@Log4j2
public class TotalController {
    private final TotalService totalService;

    @GetMapping
    ResponseEntity<List<Total>> getAllTotals() {
        return new ResponseEntity<>(totalService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    ResponseEntity<List<Total>> getAllTotalsByDate(@PathVariable String date) {
        return new ResponseEntity<>(totalService.getAllTotalsByDate(date), HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<Total> createTotal(@Validated @RequestBody Total total) {
        return new ResponseEntity<>(totalService.createTotal(total), HttpStatus.OK);
    }

    @PostMapping("/delete")
    ResponseEntity<HttpStatus> deleteTotal(@Validated @RequestBody Total total) {
        try {
            totalService.deleteTotal(total);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}