package com.mycalories.model.repository;

import com.mycalories.model.model.Food;
import com.mycalories.model.model.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TotalRepository extends JpaRepository<Total, Long> { }
