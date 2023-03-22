package com.ntu.sctp.group1.DataTransferObject;

import com.ntu.sctp.group1.entity.UserCredentials;
import com.ntu.sctp.group1.entity.Volunteer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSignInDto {

    private UserCredentials userCredentials;
    private Volunteer volunteer;

}
