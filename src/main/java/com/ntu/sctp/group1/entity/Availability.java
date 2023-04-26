package com.ntu.sctp.group1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="availability")
public class Availability {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "avail")
    private boolean avail;

    @Column(name = "timeslot")
    private String timeslot;

    @Column(name="created_at", updatable= false)
    Timestamp createdAt = new Timestamp(new Date().getTime());

    @ManyToOne
    @JoinColumn(name = "volunteer_id", referencedColumnName = "id")
    private Volunteer volunteer;

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }




}