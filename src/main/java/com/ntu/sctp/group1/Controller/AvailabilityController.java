package com.ntu.sctp.group1.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteers/availability")
public class AvailabilityController {

    @PostMapping("/{volunterrId}")
    public ResponseEntity setAvailability(@PathVariable Integer volunteerId, @RequestParam String date) {
        return null;
    }
}
