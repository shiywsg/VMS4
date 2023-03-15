package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Service.VolunteerService;
import com.ntu.sctp.group1.entity.Volunteer;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class VolunteerController {

@Autowired
VolunteerService volunteerService;

record Message(String message, boolean success){}

    @GetMapping("/volunteers")
    public ResponseEntity getAllVolunteers() {
        try {
            return ResponseEntity.ok().body(volunteerService.getAllVolunteers());
        } catch (NoVolunteerFoundExceptions ex) {
          ex.printStackTrace(); 
          return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message("No Volunteer Found!",false));
        }
    }

    @GetMapping("/volunteers/{id}")
    public ResponseEntity getVolunteerById(@PathVariable int id) throws NoVolunteerFoundExceptions {
        try {
            Volunteer volunteer = volunteerService.getVolunteerById(id);
            return ResponseEntity.ok().body(volunteer);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message("Volunteer not found with ID: " + id,false));
        }
    }

    @GetMapping("/volunteers/search")
    public ResponseEntity<List<Volunteer>> searchByParams (@RequestParam Map<String, String> params) {
        try {
            return ResponseEntity.ok().body(volunteerService.searchByParams(params));
        } catch (NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(), false));
        }
    }

   

    @PostMapping("/newvolunteer")
    public ResponseEntity createVolunteer(@RequestBody Volunteer newVolunteer) {
        try {
            Volunteer volunteer = volunteerService.createVolunteer(newVolunteer);
            return ResponseEntity.ok().body(volunteer);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(),false));
        }
    }

    @PutMapping("/volunteers/{id}")
    public ResponseEntity updateVolunteer(@PathVariable int id, @RequestBody Volunteer updatedVolunteer) {
        try {
            Volunteer volunteer = volunteerService.updateVolunteer(id, updatedVolunteer);
            return ResponseEntity.ok().body(volunteer);
        } catch (NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message("Unable to update volunteer",false));
        }
    }

    @DeleteMapping("/volunteer/{id}")
    public ResponseEntity deleteVolunteer(@PathVariable Integer id) {
    try {
        Volunteer volunteer = volunteerService.deleteVolunteer(id);
        return ResponseEntity.ok().build();
    } catch (NoVolunteerFoundExceptions ex) {
        ex.printStackTrace();
        return ResponseEntity.notFound().build();
    } catch (Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(new Message("Unable to update volunteer",false));
    }
    }

}