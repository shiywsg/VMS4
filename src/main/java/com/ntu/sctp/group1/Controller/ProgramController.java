package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.Exceptions.NoProgramFoundExceptions;
import com.ntu.sctp.group1.Service.ProgramService;
import com.ntu.sctp.group1.entity.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/admin")
public class ProgramController {

    @Autowired
    ProgramService programService;

    record Message(String message, boolean success){}

    @GetMapping("/programs")
    public ResponseEntity<?> getAllVolunteers() {
        try {
            return ResponseEntity.ok().body(programService.getAllPrograms());
        } catch (NoProgramFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(),false));
        }
    }

    @GetMapping("/programs/{id}")
    public ResponseEntity<?> getProgramById(@PathVariable Integer id) {
        try {
            Program program = programService.getProgramById(id);
            return ResponseEntity.ok(program);
        } catch (NoProgramFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(),false));
        }
    }

    @PostMapping("/newprogram")
    public ResponseEntity<?> createProgram(@RequestBody Program newProgram) {
        try {
            Program program = programService.createProgram(newProgram);
            return ResponseEntity.ok().body(program);
        } catch (NoProgramFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(),false));
        }
    }

    @PutMapping("/programs/{id}")
    public ResponseEntity<?> updateProgram(@PathVariable Integer id, @RequestBody Program updatedProgram) {
        try {
            Program program = programService.updateProgram(id, updatedProgram);
            return ResponseEntity.ok(program);
        } catch (NoProgramFoundExceptions ex)  {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(),false));
        }
    }

    @DeleteMapping("/programs/{id}")
    public ResponseEntity<?> deleteProgram(@PathVariable Integer id) {
        try {
            programService.deleteProgram(id);
            return ResponseEntity.ok(new Message("Program with ID " + id + " deleted successfully", true));
        } catch (NoProgramFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(),false));
        }
    }
}
