package com.mycalories.model.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "total")
public class Total {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "gram")
    private int gram;

    @OneToOne
    @JoinColumn(name = "food")
    private Food food;

}
