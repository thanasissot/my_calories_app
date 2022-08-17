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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/total")
@RequiredArgsConstructor
@Log4j2
public class TotalController {
    private final TotalService totalService;
    private final FoodService foodService;
    private final SimpleDateFormat fromFormat = new SimpleDateFormat("yyyyMMdd");
    private final SimpleDateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping
    ResponseEntity<List<Total>> getAllTotals() {
        return new ResponseEntity<>(totalService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Total> createTotal(@Validated @RequestBody Total total) {
        return new ResponseEntity<>(totalService.createTotal(total), HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    ResponseEntity<Object> getAllTotalsByDate(@PathVariable String date) {
        fromFormat.setLenient(false);
        toFormat.setLenient(false);
        Date date_;
        try {
            date_ = fromFormat.parse(date);
            List<Total> totalList = totalService.getAllTotalsByDate(toFormat.format(date_));
            return new ResponseEntity<>(totalList, HttpStatus.OK);

        } catch (ParseException e) {
            log.error("Date could not be parsed");
            log.error(e.getLocalizedMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong date format");
    }

}