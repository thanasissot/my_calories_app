package com.mycalories.model2.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query(value = "SELECT * FROM meal t WHERE t.date = :date",
            nativeQuery = true)
    List<Meal> findByDate(@Param("date") String date);
}
