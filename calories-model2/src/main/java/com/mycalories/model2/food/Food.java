package com.mycalories.model2.food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "food")
@AllArgsConstructor
@NoArgsConstructor
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
