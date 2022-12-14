package com.mycalories.caloriesrest.service;

import com.mycalories.model2.total.Total;
import com.mycalories.model2.total.TotalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalService {
    private final TotalRepository totalRepository;

    public List<Total> findAll(){
        return totalRepository.findAll();
    }

    public Total createTotal(Total total) {
        return totalRepository.save(total);
    }

    public List<Total> getAllTotalsByDate(String date) {
        return totalRepository.findByDate(date);
    }

    public void deleteTotal(Total total) {
        totalRepository.delete(total);
    }

}
