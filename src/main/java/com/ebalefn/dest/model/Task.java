package com.ebalefn.dest.model;

import lombok.Data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private LocalDate deadlineDate;

    private int status; // 0 - created, 1 - in progress, 2 - done, 3 - failed
    private Long assignedUserId;
}
