package com.strangersprings.zpr.client.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "index_entry_type")
public class IndexEntryType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "index_type_id")
    private IndexType type;

    @Column(name = "window_size")
    private Integer windowSize;
}
