package com.ntu.sctp.group1.Controller;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntu.sctp.group1.Exceptions.NoEnrolmentFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.repository.EnrolmentRepository;
import com.ntu.sctp.group1.Service.EnrolmentService;
import com.ntu.sctp.group1.entity.Enrolment;
import com.ntu.sctp.group1.entity.Program;
import com.ntu.sctp.group1.entity.Volunteer;

@RestController
@RequestMapping("/admin/enrolment")
public class EnrolmentController {

    @Autowired 
    EnrolmentService enrolmentService;

    record Status(String msg, boolean success){};

    @GetMapping
        public ResponseEntity<?> getAllEnrolments() {
            try {
                return ResponseEntity.ok().body(enrolmentService.getAllEnrolments());
            } catch (NoEnrolmentFoundExceptions ex) {
              ex.printStackTrace();
              return ResponseEntity.notFound().build();
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
            } 
        }

        @PostMapping("/newenrolment")
    public ResponseEntity<?> createEnrolment(@RequestBody Enrolment newEnrolment) {
        try {
            String day = newEnrolment.getDayOfProgram();
            String time = newEnrolment.getTimeOfProgram();
            Program program = newEnrolment.getProgram();
            Set<Volunteer> volunteers = newEnrolment.getVolunteers();

            Enrolment enrolment = enrolmentService.createEnrolment(day, time, program, volunteers);
            return ResponseEntity.ok().body(enrolment);
        } catch (NoEnrolmentFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

    // @PostMapping("/newenrolment")
    // public ResponseEntity<?> createEnrolment(@RequestBody String date, Integer programid){
    //     try {
    //         return ResponseEntity.ok().body(enrolmentService.createEnrolment(date, programid));
    //     } catch(NoEnrolmentFoundExceptions ex) {
    //         ex.printStackTrace();
    //         return ResponseEntity.notFound().build();
    //     } catch(Exception ex) {
    //         ex.printStackTrace();
    //         return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
    //     }
    // }

    @PutMapping("/update/{id}")
        public ResponseEntity<?> updateEnrolment(@PathVariable Integer id, @RequestBody Enrolment updatedEnrolment) {
            try {
                Enrolment enrolment = enrolmentService.updateEnrolment(id, updatedEnrolment);
                return ResponseEntity.ok().body(enrolment);
            } catch (NoEnrolmentFoundExceptions ex) {
                ex.printStackTrace();
                return ResponseEntity.notFound().build();
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
            }
        }
}
