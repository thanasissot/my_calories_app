package com.mycalories.model2.total;

import com.mycalories.model2.food.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "total")
@AllArgsConstructor
@NoArgsConstructor
public class Total {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "gram")
    private int gram;

    @OneToOne
    @JoinColumn(name = "food")
    private Food food;

}
