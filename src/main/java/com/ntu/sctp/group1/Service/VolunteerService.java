package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.entity.*;
import com.ntu.sctp.group1.repository.ProfileRepository;
import com.ntu.sctp.group1.repository.UserRepository;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VolunteerService {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    ProfileRepository profileRepository;

    //Keep this
    @Autowired
    UserRepository userRepo;


    
    public List<Volunteer> getAllVolunteers() throws NoVolunteerFoundExceptions {

        List<Volunteer> volunteers = volunteerRepository.findAll();
        if (volunteers.isEmpty()) {
             throw new NoVolunteerFoundExceptions("No Volunteer Found!");
        } else {
            return volunteers;
        }
    }

    public Volunteer getVolunteerById(int id) throws NoVolunteerFoundExceptions {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        if (volunteer.isPresent()) {
            return volunteer.get();
        } else {
            throw new NoVolunteerFoundExceptions ("Volunteer not found with ID: " + id);
        }
    }

   
   
    public List<Volunteer> searchByParams (Map <String, String> params) throws NoVolunteerFoundExceptions {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        if (volunteers.size() == 0) {
            throw new NoVolunteerFoundExceptions("No volunteers meeting this criteria found");
        }
        List<Volunteer> filteredList = new ArrayList<>();
        if (params.containsKey("experience")) {
            filteredList = volunteers.stream()
                            .filter((volunteer) -> volunteer.getEducation().equalsIgnoreCase(params.get("education")) ||
                                                   volunteer.getLanguage().equalsIgnoreCase(params.get("language")) ||
                                                   volunteer.getPastExperience().contains(params.get("experience")))
                            .toList();
        } else {
            filteredList = volunteers.stream()
                            .filter((volunteer) -> volunteer.getEducation().equalsIgnoreCase(params.get("education")) ||
                                                   volunteer.getLanguage().equalsIgnoreCase(params.get("language")))
                            .toList();
        }

        if(filteredList.size() == 0) {
            throw new NoVolunteerFoundExceptions("No results found!");
        }

        return filteredList;
    }


    public Volunteer createVolunteer(Volunteer newVolunteer, String uid) throws NoVolunteerFoundExceptions {
        if (newVolunteer.getName().isEmpty()) {
            throw new NoVolunteerFoundExceptions("Volunteer's name and email cannot be empty");
        }

        Volunteer newPerson = volunteerRepository.save(newVolunteer);
        if(volunteerRepository.findById(newPerson.getId()).isEmpty()) {
            throw new NoVolunteerFoundExceptions("Failed to save volunteer");
        }
        // Keep this
        UserCredentials user = new UserCredentials();
        user.setVolunteerId(newPerson.getId());
        user.setUsername(newPerson.getEmail());
        user.setUid(uid);
        user.setTokenIsActive(false);
        user.setRole(Role.USER);
        userRepo.save(user);

        Profile newProfile = new Profile();
        newProfile.setVolunteer(newPerson);
        profileRepository.save(newProfile);
        return newPerson;
    }

    public Volunteer updateVolunteer(int id, Volunteer updatedVolunteer) throws NoVolunteerFoundExceptions {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        if (volunteer.isPresent()) {
            Volunteer existingVolunteer = volunteer.get();
            existingVolunteer.setName(updatedVolunteer.getName());
            existingVolunteer.setEmail(updatedVolunteer.getEmail());
            existingVolunteer.setContact(updatedVolunteer.getContact());
            existingVolunteer.setAddress(updatedVolunteer.getAddress());
            existingVolunteer.setEducation(updatedVolunteer.getEducation());
            existingVolunteer.setOccupation(updatedVolunteer.getOccupation());
            existingVolunteer.setLanguage(updatedVolunteer.getLanguage());
            existingVolunteer.setLanguage2(updatedVolunteer.getLanguage2());
            existingVolunteer.setLanguage3(updatedVolunteer.getLanguage3());
            existingVolunteer.setPastExperience(updatedVolunteer.getPastExperience());
            existingVolunteer.setDateOfBirth(updatedVolunteer.getDateOfBirth());
            existingVolunteer.setReferrerName(updatedVolunteer.getReferrerName());
            existingVolunteer.setReferrerContact(updatedVolunteer.getReferrerContact());

            return volunteerRepository.save(existingVolunteer);
        } else {
            throw new NoVolunteerFoundExceptions("Volunteer not found with ID: " + id);
        }
    }

    public void deleteVolunteer(int id) throws NoVolunteerFoundExceptions {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        if (volunteer.isPresent()) {
            Volunteer existingVolunteer = volunteer.get();
            volunteerRepository.delete(existingVolunteer);
        } else {
            throw new NoVolunteerFoundExceptions("Volunteer not found with ID: " + id);
        }
    }

    // NEWLY ADDED ON 29 Mar
    public List<Enrolment> getEnrolmentsOfVolunteer(int volunteerId) throws NoVolunteerFoundExceptions {
        Optional<Volunteer> volunteer = volunteerRepository.findById(volunteerId);
        if(volunteer.isEmpty()) {
            throw new NoVolunteerFoundExceptions("Volunteer not found with ID: " + volunteerId);
        }
        Set<Enrolment> enrolments  = volunteer.get().getEnrolments();
        return new ArrayList<>(enrolments);
    }


}
