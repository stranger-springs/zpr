package com.strangersprings.zpr.client.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "aggregation_entry")
public class AggregationEntry {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aggregation_id")
    private Aggregation aggregation;

    @ManyToOne
    @JoinColumn(name = "entry_type_id")
    private AggregationEntryType type;

    private BigDecimal value;
}
