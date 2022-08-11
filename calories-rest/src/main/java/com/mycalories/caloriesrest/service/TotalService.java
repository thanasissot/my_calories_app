package com.mycalories.caloriesrest.service;

import com.mycalories.model2.model.Total;
import com.mycalories.model2.repository.TotalRepository;
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

}
