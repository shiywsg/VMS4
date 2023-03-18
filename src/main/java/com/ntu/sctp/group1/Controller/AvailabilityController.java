package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteers/availability")
public class AvailabilityController {

    @Autowired
    AvailabilityService availabilityService;


    record Status(String msg, boolean success){};

    @PostMapping("/{volunteerId}")
    public ResponseEntity setAvailability(@PathVariable Integer volunteerId, @RequestParam String date) {
        try {
            return ResponseEntity.ok().body(availabilityService.setAvailability(volunteerId, date));
        } catch(NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false) );
        }
    }
}
