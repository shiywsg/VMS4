package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.DataTransferObject.ProfileEditDto;
import com.ntu.sctp.group1.Exceptions.NoProfileFoundExceptions;
import com.ntu.sctp.group1.entity.Profile;
import com.ntu.sctp.group1.entity.Role;
import com.ntu.sctp.group1.entity.UserCredentials;
import com.ntu.sctp.group1.repository.ProfileRepository;
import com.ntu.sctp.group1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepo;



    public List<Profile> getAllProfiles(String uid) throws NoProfileFoundExceptions {
        if (checkIfUserIsAdmin(uid)) {
            List<Profile> allProfiles = profileRepository.findAll();
            if(allProfiles.size() == 0) {
                throw new NoProfileFoundExceptions("There are no profiles found in repository");
            }
            return allProfiles;
        }
        return null;
    }

    public Profile getProfileById(Integer id) throws NoProfileFoundExceptions {
        Optional<Profile> profile = profileRepository.findByVolunteerId(id);
        if (profile.isPresent()) {
            return profile.get();
        } else {
            throw new NoProfileFoundExceptions("Profile not found for the given ID: " + id);
        }
    }

    public List<Profile> findAll() throws NoProfileFoundExceptions {
        List<Profile> profile = profileRepository.findAll();
        if(profile.size() == 0) {
            throw new NoProfileFoundExceptions("No profile found.");
        }
        return profile;
    }

    public Profile editProfile(Integer volunteerid, ProfileEditDto newVolunteerProfile)
            throws NoProfileFoundExceptions{
        Optional<Profile> findProfile = profileRepository.findByVolunteerId(volunteerid);
        if (findProfile.isEmpty()){
            throw new NoProfileFoundExceptions("now profile found");
        }
        Profile existingProfile = findProfile.get();
        existingProfile.setHobbies(newVolunteerProfile.getHobbies());
        existingProfile.setInterests(newVolunteerProfile.getInterests());
        existingProfile.setProfessionalExperience(newVolunteerProfile.getProfessionalExperience());
        existingProfile.setProfilePicture(newVolunteerProfile.getProfilePicture());

        return profileRepository.save(existingProfile);
    }

    public boolean checkIfUserIsAdmin(String uid) {
        Optional<UserCredentials> userOptional = userRepo.findByUid(uid);
        if (userOptional.isEmpty()) return false;
        return userOptional.get().getRole().equals(Role.ADMIN);
    }
}
