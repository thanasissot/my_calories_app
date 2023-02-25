package com.mycalories.model2.meal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "meal")
@AllArgsConstructor
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "gram")
    private int gram;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "calories")
    private double calories;

}
