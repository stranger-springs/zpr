package com.strangersprings.zpr.client.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "currency_type")
public class CurrencyType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String name;
}
