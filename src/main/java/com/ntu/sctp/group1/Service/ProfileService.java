package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.NoProfileFoundExceptions;
import com.ntu.sctp.group1.entity.Profile;
import com.ntu.sctp.group1.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile getProfileById(Integer id) throws NoProfileFoundExceptions {
        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isPresent()) {
            return profile.get();
        } else {
            throw new NoProfileFoundExceptions("Profile not found for the given ID: " + id);
        }
    }
}
