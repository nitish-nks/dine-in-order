package com.example.dio.model;

import com.example.dio.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tables")
@Getter
@Setter
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tableId;

    @Column(name = "TableNumber")
    private long tableNo;

    @Column(name = "TotalCapacity")
    private long tableCapacity;

    @Column(name = "TableStatus")
    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    @ManyToOne
    private Restaurant restaurant;
}
