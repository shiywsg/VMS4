package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.entity.Profile;
import com.ntu.sctp.group1.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Optional<Profile> getProfileById(Integer id) {
        return profileRepository.findById(id);
    }
}
