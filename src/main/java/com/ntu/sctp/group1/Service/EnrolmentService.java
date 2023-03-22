package com.ntu.sctp.group1.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntu.sctp.group1.Exceptions.NoEnrolmentFoundExceptions;
import com.ntu.sctp.group1.entity.Enrolment;
import com.ntu.sctp.group1.entity.Program;
import com.ntu.sctp.group1.repository.EnrolmentRepository;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import com.ntu.sctp.group1.repository.ProgramRepository;

@Service
public class EnrolmentService {
    
    @Autowired
    EnrolmentRepository enrolmentRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    ProgramRepository programRepository;

    public List<Enrolment> getAllEnrolments() throws NoEnrolmentFoundExceptions {
        List<Enrolment> enrolment = enrolmentRepository.findAll();
        if (enrolment.isEmpty()) {
             throw new NoEnrolmentFoundExceptions("No enrolments found");
        } else {
            return enrolment;
        }
    }

    public Enrolment createEnrolment(Integer programid, String date) throws NoEnrolmentFoundExceptions {
        Optional<Program> findProgram = programRepository.findById(programid);
        if(findProgram.isEmpty()) {
            throw new NoEnrolmentFoundExceptions("No program with this ID found");
        }
        Enrolment programDate = new Enrolment();
        programDate.setDate(LocalDate.parse(date));
        
        return enrolmentRepository.save(programDate);
    }

    public Enrolment updateEnrolment(Integer id, Enrolment updatedEnrolment) throws NoEnrolmentFoundExceptions {
        Optional<Enrolment> enrolment = enrolmentRepository.findById(id);
        if (enrolment.isPresent()) {
            Enrolment existingEnrolment = enrolment.get();
            existingEnrolment.setDate(updatedEnrolment.getDate());
            existingEnrolment.setProgram(updatedEnrolment.getProgram());
    
            return enrolmentRepository.save(existingEnrolment);
        } else {
            throw new NoEnrolmentFoundExceptions("Enrolment not found with ID: " + id);
        }
    }
}
