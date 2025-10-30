package com.synergy.bokja.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cycle_table")
public class CycleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cyno;

    @Column(nullable = false)
    private Long umno;

    @Column(nullable = false)
    private int total_cycle;

    @Column
    private Integer cur_cycle;

    @Column
    private Integer save_cycle;

    @Column(nullable = false)
    private java.sql.Date start_date;

    @Column(nullable = false)
    private java.sql.Date end_date;
}