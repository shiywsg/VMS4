package com.ntu.sctp.group1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

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

    @Column(name = "volunteers_required")
    private Integer volunteersRequired;

    @Column(name = "number_of_volunteers")
    private Integer noOfVolunteers;

    @Column(name="created_at", updatable= false)
    Timestamp createdAt = new Timestamp(new Date().getTime());

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
