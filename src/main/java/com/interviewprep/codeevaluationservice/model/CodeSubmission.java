package com.interviewprep.codeevaluationservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CodeSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String language;

    @Column(length = 5000)
    private String code;
    private String status;
    private String output;
}
