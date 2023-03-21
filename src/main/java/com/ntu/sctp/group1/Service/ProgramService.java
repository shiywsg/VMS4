package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.NoProgramFoundExceptions;
import com.ntu.sctp.group1.entity.Program;
import com.ntu.sctp.group1.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Program getProgramById(Integer id) throws NoProgramFoundExceptions {
        Optional<Program> program = programRepository.findById(id);
        if (program.isPresent()) {
            return program.get();
        } else {
            throw new NoProgramFoundExceptions("Program not found with ID: " + id);
        }
    }


    public Program createProgram(Program program) throws NoProgramFoundExceptions {
        if (program.getName().isEmpty()) {
            throw new NoProgramFoundExceptions("Program name cannot be empty");
        }
        return programRepository.save(program);
    }

    public Program updateProgram(Integer id, Program updatedProgram) throws NoProgramFoundExceptions {
        Optional<Program> findProgram = programRepository.findById(id);

        if (findProgram.isPresent()) {
            Program existingProgram = findProgram.get();
            existingProgram.setName(updatedProgram.getName());
            existingProgram.setDayOfProgram(updatedProgram.getDayOfProgram());
            existingProgram.setTimeOfProgram(updatedProgram.getTimeOfProgram());
            existingProgram.setNoOfVolunteers(updatedProgram.getNoOfVolunteers());
            existingProgram.setVolunteersRequired(updatedProgram.getVolunteersRequired());

            return programRepository.save(existingProgram);
        } else {

            throw new NoProgramFoundExceptions("Program not found with id: " + id);
        }
    }

    public void deleteProgram(Integer id) throws NoProgramFoundExceptions {
        Optional<Program> program = programRepository.findById(id);
        if (program.isPresent()) {
            Program existingProgram = program.get();
            programRepository.delete(existingProgram);
        } else {
            throw new NoProgramFoundExceptions("Program not found with id: " + id);
        }
    }
}
