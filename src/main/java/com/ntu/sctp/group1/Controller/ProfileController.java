package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.DataTransferObject.ProfileEditDto;
import com.ntu.sctp.group1.Exceptions.NoProfileFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Service.ProfileService;
import com.ntu.sctp.group1.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volunteer/profiles")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    record Message(String message, boolean success) {}

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Integer id) {
        try {
            Profile profile = profileService.getProfileById(id);
            return ResponseEntity.ok(profile);
        } catch (NoProfileFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(), false));
        }
    }

    @PutMapping("/{volunteerid}/edit")
    public ResponseEntity editVolunteerProfile(@PathVariable Integer volunteerid, ProfileEditDto newVolunteerProfile) {
        try{
            return ResponseEntity.ok().body(profileService.editProfile(volunteerid, newVolunteerProfile));
        } catch (NoProfileFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(),false));
        }
    }
}
