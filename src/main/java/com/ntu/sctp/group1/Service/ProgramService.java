package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.NoProgramFoundExceptions;
import com.ntu.sctp.group1.entity.Program;
import com.ntu.sctp.group1.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    ProgramRepository programRepository;

    public List<Program> getAllPrograms() throws NoProgramFoundExceptions {
        List<Program> programs = programRepository.findAll();
        if (programs.isEmpty()) {
            throw new NoProgramFoundExceptions ("No Program Found!");
        } else {
            return programs;
        }
    }
}
