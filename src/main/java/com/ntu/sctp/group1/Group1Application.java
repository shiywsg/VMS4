package com.ntu.sctp.group1;

import com.ntu.sctp.group1.entity.Profile;
import com.ntu.sctp.group1.entity.Role;
import com.ntu.sctp.group1.entity.UserCredentials;
import com.ntu.sctp.group1.entity.Volunteer;
import com.ntu.sctp.group1.repository.AvailabilityRepository;
import com.ntu.sctp.group1.repository.ProfileRepository;
import com.ntu.sctp.group1.repository.UserRepository;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;


@SpringBootApplication
public class Group1Application {

	@Autowired
	VolunteerRepository volunteerRepo;

	@Autowired
	ProfileRepository profileRepo;

	@Autowired
	AvailabilityRepository availRepo;

	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(Group1Application.class, args);
	}

	@PostConstruct
	public void seeder () {

		UserCredentials user = new UserCredentials();
		UserCredentials user2 = new UserCredentials();
		UserCredentials admin = new UserCredentials();

		Profile profile = new Profile();
		Profile profile2 = new Profile();
		Profile profile3 = new Profile();

		Volunteer person = new Volunteer("user1", "user1@gmail.com");
		person.setAddress("Blk 233, Bishan street 12");
		person.setDateOfBirth(LocalDate.parse(("1980-07-01")));
		person.setContact("98273628");
		person.setEducation("degree");
		person.setLanguage("english");
		person.setHasCriminalRecord(false);
		person.setPastExperience("soup kitchen, child care");
		person.setReferrerName("Oliver");
		person.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person2 = new Volunteer("user2", "user2@gmail.com");
		person2.setAddress("Blk 233, Ang mo kio street 12");
		person2.setDateOfBirth(LocalDate.parse(("1990-07-01")));
		person2.setContact("987979792");
		person2.setEducation("master");
		person2.setLanguage("english");
		person2.setHasCriminalRecord(false);
		person2.setPastExperience("yoga");
		person2.setReferrerName("Oliver");
		person2.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person3 = new Volunteer("admin", "admin@gmail.com");
		person3.setAddress("Blk 233, yishun street 12");
		person3.setDateOfBirth(LocalDate.parse(("1997-07-01")));
		person3.setContact("937464545");
		person3.setEducation("degree");
		person3.setLanguage("english");
		person3.setHasCriminalRecord(false);
		person3.setPastExperience("teaching");
		person3.setReferrerName("Oliver");
		person3.setReferrerContact(Long.parseLong("92378229"));

		person = volunteerRepo.save(person);
		person2 = volunteerRepo.save(person2);
		person3 = volunteerRepo.save(person3);

		user.setUsername(person.getEmail());
		user.setUid("aId0fTgOlKdrNpCd6IapWFnBC2D3");
		user.setTokenIsActive(false);
		user.setVolunteerId(person.getId());
		user.setRole(Role.USER);
		userRepo.save(user);

		user2.setUsername(person2.getEmail());
		user2.setUid("Bq1SrD7WrHY4TNoBIY2Nw4v7K6x1");
		user2.setTokenIsActive(false);
		user2.setVolunteerId(person2.getId());
		user2.setRole(Role.USER);
		userRepo.save(user2);

		admin.setUsername(person3.getEmail());
		admin.setUid("YH70F63hN4gx3FgKW5yQJYP14az2");
		admin.setTokenIsActive(false);
		admin.setVolunteerId(person3.getId());
		admin.setRole(Role.ADMIN);
		userRepo.save(admin);

		profile.setVolunteer(person);
		profile2.setVolunteer(person2);
		profile3.setVolunteer(person3);
		profileRepo.save(profile);
		profileRepo.save(profile2);
		profileRepo.save(profile3);



//		Availability avail = new Availability();
//		avail.setDate(LocalDate.parse(("2025-07-01")));
//		avail.setAvail(true);
//		availRepo.save(avail);


	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
