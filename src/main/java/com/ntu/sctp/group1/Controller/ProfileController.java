package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.Exceptions.NoProfileFoundExceptions;
import com.ntu.sctp.group1.Service.ProfileService;
import com.ntu.sctp.group1.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/volunteer/profiles")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    record Message(String message, boolean success){}

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Integer id) {
        try {
            Optional<Profile> profile = profileService.getProfileById(id);
            if (profile.isPresent()) {
                return ResponseEntity.ok(profile.get());
            } else {
                throw new NoProfileFoundExceptions("Profile not found for the given ID: " + id);
            }
        } catch (NoProfileFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Message(ex.getMessage(),false));
        }
    }
}

