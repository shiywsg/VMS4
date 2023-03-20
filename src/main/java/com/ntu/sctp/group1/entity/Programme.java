package com.ntu.sctp.group1.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="programme")
public class Programme {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "name")
    private String name = "";

    @Column(name = "day")
    private String day = "";

    @Column(name = "time")
    private String time = "";

    @Column(name = "number_of_volunteers")
    private Integer noOfVolunteers;

    @Column(name = "volunteers_required")
    private Integer volunteersRequired;
}