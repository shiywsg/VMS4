package com.ntu.sctp.group1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


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
            private Integer id;

            @NonNull
            @NotBlank(message = "Name cannot be blank!")
            @Column(name = "name", nullable = false)
            private String name;

            //amend age to dateOfBirth
            @Column(name = "date_of_birth")
            private LocalDate dateOfBirth;

            @NonNull
            @NotBlank(message = "Email cannot be blank!")
            @Column(name = "email", nullable = false)
            private String email;

            @Column(name = "contact")
            private Integer contact;

            @Column(name = "address")
            private String address;

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
            private Integer referrerContact;


//            @OneToOne(cascade = CascadeType.ALL)
//            @JoinColumn(name = "profile_id", referencedColumnName = "id")
//            private Profile profile;
//
        @JsonIgnore
        @OneToOne(mappedBy = "volunteer", cascade = CascadeType.ALL)
        private Profile profile;

        @JsonIgnore
        @OneToMany (mappedBy = "volunteer", cascade = CascadeType.ALL)
        List<Availability> availabilities;
}