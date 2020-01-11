package com.strangersprings.zpr.client.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "aggregation")
public class Aggregation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "first_id")
    private Currency first;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "last_id")
    private Currency last;

    @OneToMany(mappedBy = "aggregation", fetch = LAZY, cascade = PERSIST)
    private List<AggregationEntry> entries;
}
