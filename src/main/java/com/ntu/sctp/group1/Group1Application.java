package com.ntu.sctp.group1;

import com.ntu.sctp.group1.entity.Profile;
import com.ntu.sctp.group1.entity.Volunteer;
import com.ntu.sctp.group1.repository.ProfileRepository;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Group1Application {

	@Autowired
	VolunteerRepository volunteerRepository;

	@Autowired
	ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(Group1Application.class, args);
	}

	@PostConstruct
	public void createVolunteer () {
		Volunteer person1 = new Volunteer("ace", "ace@mail.com");
		Volunteer person2 = new Volunteer("jonathan", "jonathan@mail.com");

		volunteerRepository.save(person1);
		volunteerRepository.save(person2);

		Profile profile1 = new Profile();
		profile1.setInterests("Photography, Travel");
		profile1.setHobbies("Hiking, Reading");
		profile1.setProfessionalExperience("Software Developer");
		profile1.setVolunteer(person1);

		Profile profile2 = new Profile();
		profile2.setInterests("Sports, Music");
		profile2.setHobbies("Basketball, Playing Guitar");
		profile2.setProfessionalExperience("Graphic Designer");
		profile2.setVolunteer(person2);

		profileRepository.save(profile1);
		profileRepository.save(profile2);
	}
}
