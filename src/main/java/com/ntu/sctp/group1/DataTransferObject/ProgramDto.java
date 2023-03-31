package com.ntu.sctp.group1.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProgramDto {

    private String name;
    private String date;
    private String description;
    private String photo = "";
    private String timeOfProgram;
    private Integer volunteersRequired;
    private Integer noOfVolunteers;
}
