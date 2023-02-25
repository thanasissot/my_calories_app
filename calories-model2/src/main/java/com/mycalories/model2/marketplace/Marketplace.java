package com.mycalories.model2.marketplace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "marketplace")
@AllArgsConstructor
@NoArgsConstructor
public class Marketplace {
    @Id
    @Column(name = "name")
    private String name;

}
