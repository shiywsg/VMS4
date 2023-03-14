package com.ntu.sctp.group1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Service.VolunteerService;

@RestController
public class VolunteerController {

@Autowired
VolunteerService volunteerService;

record Message(String message, boolean success){}

@GetMapping("/admin/volunteers")

    public ResponseEntity getAllVolunteers() {
        try {
            return ResponseEntity.ok().body(volunteerService.getAllVolunteers());
        } catch (NoVolunteerFoundExceptions ex) {
          ex.printStackTrace(); 
          return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message("Server Error",false)); 
        }
        
    }
}

