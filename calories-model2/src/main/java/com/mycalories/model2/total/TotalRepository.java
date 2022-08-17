package com.mycalories.model2.total;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalRepository extends JpaRepository<Total, Long> {
    @Query(value = "SELECT * FROM total t WHERE t.date = :date",
            nativeQuery = true)
    List<Total> findByDate(@Param("date") String date);
}
