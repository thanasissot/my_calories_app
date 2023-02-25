package com.mycalories.model2.item;

import com.mycalories.model2.marketplace.Marketplace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "marketplace")
    private Marketplace marketplace;

    @Column(name = "formattedValue")
    private String formattedValue;

    @Column(name = "value")
    private double value;
}
