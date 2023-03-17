package com.ntu.sctp.group1.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileEditDto {

    private String interests;
    private String hobbies;
    private String professionalExperience;
    private String profilePicture;
}
