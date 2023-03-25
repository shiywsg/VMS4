package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.Exceptions.NoAvailabilityFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Service.AvailabilityService;
import com.ntu.sctp.group1.entity.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 86400, allowCredentials = "false" )
@RestController
public class AvailabilityController {

    @Autowired
    AvailabilityService availabilityService;

    record Status(String msg, boolean success){};

    @PostMapping("/volunteers/availability/{volunteerId}")
    public ResponseEntity<?> setAvailability(@PathVariable Integer volunteerId, @RequestParam String date, @RequestParam String timeslot) {
        try {
            return ResponseEntity.ok().body(availabilityService.setAvailability(volunteerId, date, timeslot));
        } catch(NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false) );
        }
    }

    @GetMapping("/volunteers/availability/date/{date}")
    public ResponseEntity<?> searchByDate(@PathVariable String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            List<Volunteer> volunteers = availabilityService.searchByDate(parsedDate);
            return ResponseEntity.ok().body(volunteers);
        } catch (NoAvailabilityFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

    @PutMapping("/volunteers/availability/{volunteerId}")
    public ResponseEntity<?> updateAvailability(@PathVariable Integer volunteerId,
                                                @RequestParam String date,
                                                @RequestParam(required = true) Boolean isAvail) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            availabilityService.updateAvailability(volunteerId, parsedDate, isAvail);
            return ResponseEntity.ok().body(new Status("Availability updated successfully", true));
        } catch (NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

}
