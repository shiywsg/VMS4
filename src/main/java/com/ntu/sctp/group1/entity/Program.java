package com.ntu.sctp.group1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "day_of_program")
    private String dayOfProgram;

    @Column(name = "time_of_program")
    private String timeOfProgram;

    @Column(name = "number_of_volunteers")
    private Integer noOfVolunteers;

    @Column(name = "volunteers_required")
    private Integer volunteersRequired;
}
