package com.mycalories.model2.repository;

import com.mycalories.model2.model.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TotalRepository extends JpaRepository<Total, Long> { }
