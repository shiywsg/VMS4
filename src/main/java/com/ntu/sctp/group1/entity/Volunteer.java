package com.ntu.sctp.group1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
    @Table(name="volunteer")
    public class Volunteer {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            @NonNull
            @NotBlank(message = "Name cannot be blank!")
            @Column(name = "name", nullable = false)
            private String name;

            // @NonNull
            // @NotBlank(message = "Age cannot be blank!")
            @Column(name = "age")
            private Integer age;

            @NonNull
            @NotBlank(message = "Email cannot be blank!")
            @Column(name = "email", nullable = false)
            private String email;

            @Column(name = "contact")
            private String contact;

            @Column(name = "education")
            private String education = "";

            @Column(name = "language")
            private String language = "";

            @Column(name = "past_experience")
            private String pastExperience = "";

            @Column(name = "criminal_record")
            private boolean hasCriminalRecord;

            @Column(name = "referrer_name")
            private String referrerName = "";

            @Column(name = "referrer_contact")
            private String referrerContact = "";


}