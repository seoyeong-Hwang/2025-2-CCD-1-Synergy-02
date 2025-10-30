package com.synergy.bokja.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "report_table")

public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @Column(nullable = false)
    private Long umno;

    @Column(nullable = false)
    private Long cdno;

    @Column(nullable = false)
    private Long cyno;

    @Column(nullable = false)
    private Long umino;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
