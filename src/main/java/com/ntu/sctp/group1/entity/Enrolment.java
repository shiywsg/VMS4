package com.ntu.sctp.group1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "enrolment")
public class Enrolment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "day_of_program")
    private String dayOfProgram;

    @Column(name = "time_of_program")
    private String timeOfProgram;

    @OneToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToMany
    @JoinTable(name = "enrolment_volunteer", joinColumns = @JoinColumn(name = "enrolment_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "volunteer_id", referencedColumnName = "id"))
    private Set<Volunteer> volunteers;
}
