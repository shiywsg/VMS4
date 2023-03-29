package com.ntu.sctp.group1;

import com.ntu.sctp.group1.entity.*;
import com.ntu.sctp.group1.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;


@SpringBootApplication
public class Group1Application {

	@Autowired
	VolunteerRepository volunteerRepo;

	@Autowired
	ProfileRepository profileRepo;

	@Autowired
	ProgramRepository programRepo;

	@Autowired
	EnrolmentRepository enrolmentRepository;

//	@Autowired
//	AvailabilityRepository availRepo;

	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(Group1Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}


	@PostConstruct
	public void seeder () {

		UserCredentials user = new UserCredentials();
		UserCredentials user2 = new UserCredentials();
		UserCredentials admin = new UserCredentials();

		Profile profile = new Profile();
		Profile profile2 = new Profile();
		Profile profile3 = new Profile();

		Volunteer person = new Volunteer("user1", "claire@mail.com");
		person.setAddress("Blk 233, Bishan street 12");
		person.setProfilePicture("https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Elon_Musk_Royal_Society_%28crop2%29.jpg/1200px-Elon_Musk_Royal_Society_%28crop2%29.jpg");
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
		person2.setProfilePicture("https://pbs.twimg.com/media/DwoqWMYWwAAow84.jpg");
		person2.setDateOfBirth(LocalDate.parse(("1990-07-01")));
		person2.setContact("987979792");
		person2.setEducation("master");
		person2.setLanguage("english");
		person2.setHasCriminalRecord(false);
		person2.setPastExperience("yoga");
		person2.setReferrerName("Oliver");
		person2.setReferrerContact(Long.parseLong("92378229"));

		Volunteer person3 = new Volunteer("admin", "admin@gmail.com");
		person3.setProfilePicture("https://i0.wp.com/digital-photography-school.com/wp-content/uploads/2016/02/Headshot-Photography-London-1052.jpeg?ssl=1");
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
		profile.setProfilePicture("https://cdn.vectorstock.com/i/preview-1x/51/48/cartoon-character-in-glasses-avatar-young-man-vector-33215148.jpg");
		profile2.setVolunteer(person2);
		profile2.setProfilePicture("https://www.shutterstock.com/image-vector/man-character-face-avatar-glasses-260nw-542759665.jpg");
		profile3.setVolunteer(person3);
		profile3.setProfilePicture("https://previews.123rf.com/images/vectorkif/vectorkif1904/vectorkif190400101/121272615-face-expression-of-stylish-modern-young-man-sad-or-tired-male-emotion-handsome-cartoon-character.jpg");
		profileRepo.save(profile);
		profileRepo.save(profile2);
		profileRepo.save(profile3);

		Program program1 = new Program();
		Enrolment enrolment1 = new Enrolment();
		program1.setTimeOfProgram("1300hrs - 1800hrs");
		program1.setName("Home Cleaning for Elderly");
		program1.setPhoto("https://assets.avenueone.sg/wp-content/uploads/2019/11/where-to-volunteer-in-Singapore-Willing-Hearts.jpeg");
		program1.setDate(LocalDate.parse(("2023-04-01")));
		program1.setDescription("Bring joy to our seniors!");
		program1.setVolunteersRequired(20);
		program1.setNoOfVolunteers(0);
		enrolment1.setProgram(program1);
		enrolment1.setTimeOfProgram(program1.getTimeOfProgram());
		enrolment1.setDate(program1.getDate());
		enrolment1.setVolunteers(new HashSet<>());

		Program program2 = new Program();
		Enrolment enrolment2 = new Enrolment();
		program2.setTimeOfProgram("0800hrs - 1200hrs");
		program2.setPhoto("https://www.ahealingheartmedical.com/assets/img/team/A-Healing-Heart-Medical-Team.webp");
		program2.setName("TEACH");
		program2.setDescription("Share your knowledge with children!");
		program2.setDate(LocalDate.parse(("2023-04-02")));
		program2.setVolunteersRequired(15);
		program2.setNoOfVolunteers(0);
		enrolment2.setProgram(program2);
		enrolment2.setTimeOfProgram(program2.getTimeOfProgram());
		enrolment2.setDate(program2.getDate());
		enrolment2.setVolunteers(new HashSet<>());

		Program program3 = new Program();
		Enrolment enrolment3 = new Enrolment();
		program3.setTimeOfProgram("0800hrs - 1200hrs");
		program3.setPhoto("https://wayd-sg.s3.ap-southeast-1.amazonaws.com/img/2020/08/20002802/20200817_Willing-Hearts_CCH9667-scaled.jpg");
		program3.setName("Soup Kitchen");
		program3.setDate(LocalDate.parse(("2023-04-03")));
		program3.setDescription("Hone your cooking skills and spread the joy to others!");
		program3.setVolunteersRequired(15);
		program3.setNoOfVolunteers(0);
		enrolment3.setProgram(program3);
		enrolment3.setTimeOfProgram(program3.getTimeOfProgram());
		enrolment3.setDate(program3.getDate());
		enrolment3.setVolunteers(new HashSet<>());

		Program program4 = new Program();
		Enrolment enrolment4 = new Enrolment();
		program4.setTimeOfProgram("0800hrs - 1200hrs");
		program4.setPhoto("https://www.sassymamasg.com/wp-content/uploads/2018/07/Singapore-Childrens-association-kids-charity-walk.jpg");
		program4.setName("Exploration");
		program4.setDate(LocalDate.parse(("2023-04-04")));
		program4.setDescription("Bring the children to various places of interests");
		program4.setVolunteersRequired(15);
		program4.setNoOfVolunteers(0);
		enrolment4.setProgram(program4);
		enrolment4.setTimeOfProgram(program4.getTimeOfProgram());
		enrolment4.setDate(program4.getDate());
		enrolment4.setVolunteers(new HashSet<>());

		programRepo.saveAll(List.of(program1, program2, program3, program4));
		enrolmentRepository.saveAll(List.of(enrolment1, enrolment2, enrolment3, enrolment4));

//		Availability avail = new Availability();
//		avail.setDate(LocalDate.parse(("2025-07-01")));
//		avail.setAvail(true);
//		availRepo.save(avail);


	}


}
