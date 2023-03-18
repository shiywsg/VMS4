package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.NoAvailabilityFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.entity.Availability;
import com.ntu.sctp.group1.entity.Volunteer;
import com.ntu.sctp.group1.repository.AvailabilityRepository;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepo;

    @Autowired
    VolunteerRepository volunteerRepository;

    // create availability of a volunteer
    public Availability setAvailability(Integer volunteerId, String date) throws NoVolunteerFoundExceptions {
        Optional<Volunteer> findVolunteer = volunteerRepository.findById(volunteerId);
        if(findVolunteer.isEmpty()) {
            throw new NoVolunteerFoundExceptions("No volunteer id founds");
        }
        Availability availDate = new Availability();
        availDate.setAvail(true);
        availDate.setVolunteer(findVolunteer.get());
        availDate.setDate(LocalDate.parse(date));

        return availabilityRepo.save(availDate);
    }

    // Get list of volunteers for a specific date

    // 1. Get the entire list of availabilities in the table
    // 2. Filter thru the entire list, then match the date field with the date param
    // 2a. Make sure to convert the date field and date param to same type for comparison
    // 3. Filter list will return the list of Availabilities (id, date, volunteer)
    // 4. Filtered list of availabilities based on search date, we map the volunteer
    // to a new array
    // 5. List<Volunteer> listOfVolunteers = filteredList.stream().map((item)-> item.getVolunteer()).toList();
    // 6. return listOfVolunteers;

    public List<Volunteer> searchByDate(LocalDate date) throws NoAvailabilityFoundExceptions {
        List<Availability> availabilities = availabilityRepo.findByDate(date);

        if (availabilities.size() == 0) {
            throw new NoAvailabilityFoundExceptions("No volunteers available on the given date");
        }

        List<Volunteer> volunteers = availabilities.stream()
                .filter(Availability::isAvail)
                .map(Availability::getVolunteer)
                .collect(Collectors.toList());

        if (volunteers.size() == 0) {
            throw new NoAvailabilityFoundExceptions("No volunteers available on the given date");
        }

        return volunteers;
    }


    public void updateAvailability(Integer volunteerId, LocalDate date, boolean isAvail)
            throws NoVolunteerFoundExceptions, NoAvailabilityFoundExceptions {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new NoVolunteerFoundExceptions("No volunteer found with the given ID: " + volunteerId));

        Availability availability = availabilityRepo.findByVolunteerAndDate(volunteer, date)
                .orElseThrow(() -> new NoAvailabilityFoundExceptions("No availability record found for the given volunteer and date"));

        availability.setAvail(isAvail);
        availabilityRepo.save(availability);
    }

}

