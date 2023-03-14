package com.ntu.sctp.group1.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "date")
    private Date date = new Date();    

    @Column(name = "avail")
    private boolean avail;
}
