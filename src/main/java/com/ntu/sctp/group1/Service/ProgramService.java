package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.NoProgramFoundExceptions;
import com.ntu.sctp.group1.entity.Enrolment;
import com.ntu.sctp.group1.entity.Program;
import com.ntu.sctp.group1.repository.EnrolmentRepository;
import com.ntu.sctp.group1.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    EnrolmentRepository enrolmentRepository;

    public List<Program> getAllPrograms() throws NoProgramFoundExceptions {
        List<Program> programs = programRepository.findAll();
        if (programs.isEmpty()) {
            throw new NoProgramFoundExceptions("No Program Found!");
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
        Program savedProgram = programRepository.save(program);

        // Create an empty Enrolment associated with the saved program
        Enrolment enrolment = new Enrolment();
        enrolment.setProgram(savedProgram);
        enrolment.setDate(null);
        enrolment.setTimeOfProgram("");
        enrolment.setVolunteers(new HashSet<>());
        enrolmentRepository.save(enrolment);

        return savedProgram;
    }

    public Program updateProgram(Integer id, Program updatedProgram) throws NoProgramFoundExceptions {
        Optional<Program> findProgram = programRepository.findById(id);

        if (findProgram.isPresent()) {
            Program existingProgram = findProgram.get();
            existingProgram.setName(updatedProgram.getName());
            existingProgram.setDate(updatedProgram.getDate());
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
            // Find the associated enrolment
            Optional<Enrolment> enrolment = enrolmentRepository.findByProgramId(id);
            // If an enrolment is found, delete it
            if (enrolment.isPresent()) {
                enrolmentRepository.delete(enrolment.get());
            }
            // Delete the program
            programRepository.delete(existingProgram);
        } else {
            throw new NoProgramFoundExceptions("Program not found with id: " + id);
        }
    }


}
