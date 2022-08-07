package com.mycalories.model.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "food")
public class Food {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private int calories;

    @Column(name = "fat")
    private float fat;

    @Column(name = "carb")
    private float carb;

    @Column(name = "protein")
    private float protein;

}
