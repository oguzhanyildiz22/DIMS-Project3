package com.sau.dims.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "advisers_studies")
public class AdviserStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "adviser_id")
    private Adviser adviser;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "study_id")
    private Study study;

    private LocalDate adviserInvolvedDate;

    private Long performance;


}
