package com.ntu.sctp.group1.Controller;

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

    @PostMapping("/newenrolment/{programid}")
    public ResponseEntity<?> createEnrolment(@PathVariable Integer programid, @RequestParam String date) {
        try {
            return ResponseEntity.ok().body(enrolmentService.createEnrolment(programid, date));
        } catch(NoEnrolmentFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

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
