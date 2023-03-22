package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.DataTransferObject.UidDto;
import com.ntu.sctp.group1.DataTransferObject.UserSignInDto;
import com.ntu.sctp.group1.Exceptions.InvalidUidException;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.entity.UserCredentials;
import com.ntu.sctp.group1.entity.Volunteer;
import com.ntu.sctp.group1.repository.UserRepository;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    VolunteerRepository volunteerRepo;

    public UserSignInDto signIn(UidDto obj) throws InvalidUidException, NoVolunteerFoundExceptions {
        Optional<UserCredentials> findUser = userRepo.findByUid(obj.getUid());
        if(findUser.isEmpty()) {
            throw new InvalidUidException("UID in credential is not valid");
        }

        UserCredentials user = findUser.get();
        user.setTokenIsActive(true);
        userRepo.save(user);

        Optional<Volunteer> volunteerInfo = volunteerRepo.findById(user.getVolunteerId());
        if(volunteerInfo.isEmpty()) {
            throw new NoVolunteerFoundExceptions("No volunteer info found");
        }

        return new UserSignInDto(user, volunteerInfo.get());
    }


    public void signout(UidDto obj) throws InvalidUidException {
        Optional<UserCredentials> findUser = userRepo.findByUid(obj.getUid());
        if(findUser.isEmpty()) {
            throw new InvalidUidException("UID in credential is not valid");
        }

        UserCredentials user = findUser.get();
        user.setTokenIsActive(false);
        userRepo.save(user);
    }

}
