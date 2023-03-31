package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.DataTransferObject.UidDto;
import com.ntu.sctp.group1.Exceptions.InvalidUidException;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Exceptions.UnauthorisedSignInException;
import com.ntu.sctp.group1.Service.UserService;
import com.ntu.sctp.group1.Service.VolunteerService;
import com.ntu.sctp.group1.entity.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"*"}, maxAge = 86400, allowCredentials = "false" )
@RestController
@RequestMapping("/api")
public class UserController {

    record Status(String message, boolean success){};

    @Autowired
    UserService userService;

    @Autowired
    VolunteerService volunteerService;

    @PostMapping("/signin")
    public ResponseEntity<?> signinUser(@RequestBody UidDto uid){
        try {
            return ResponseEntity.ok().body(userService.signIn(uid));
        } catch(InvalidUidException | NoVolunteerFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch(UnauthorisedSignInException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new Status(ex.getMessage(), false), HttpStatus.UNAUTHORIZED);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Something is wrong with the server");
        }
    }

    @PostMapping("/admin/signin")
    public ResponseEntity<?> signinAdministration(@RequestBody UidDto uidDto) {
        try {
            return ResponseEntity.ok().body(userService.administratorSignIn(uidDto));
        } catch(InvalidUidException ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status("Uid is invalid!", false));
        } catch(UnauthorisedSignInException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new Status(ex.getMessage(), false),HttpStatus.FORBIDDEN);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(new Status("Something is wrong with the server!", false));
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signoutUser(@RequestBody UidDto uid) {
        try {
            userService.signout(uid);
            return ResponseEntity.ok().body("Signout successful");
        } catch(InvalidUidException ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Something is wrong with the server");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createVolunteer(@RequestBody Volunteer newVolunteer, @RequestParam String uid) {
        try {
            Volunteer volunteer = volunteerService.createVolunteer(newVolunteer, uid);
            return ResponseEntity.ok().body(volunteer);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new VolunteerController.Message(ex.getMessage(),false));
        }
    }


}
