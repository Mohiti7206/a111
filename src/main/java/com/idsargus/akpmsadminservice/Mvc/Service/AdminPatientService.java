package com.idsargus.akpmsadminservice.Mvc.Service;

//public class AdminPatientService {
//}
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminArTeams;
import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
import com.idsargus.akpmsadminservice.Mvc.Entities.Patient;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Exception.MandatoryFieldException;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminArTeamsRepository;
//import com.idsargus.akpmsadminservice.Mvc.Repository.UserRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminPatientRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArTeamsResponseDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.PatientRequestDto;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.PatientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AdminPatientService {


    private final AdminPatientRepository adminPatientRepository;

    public AdminPatientService(AdminPatientRepository adminPatientRepository) {
        this.adminPatientRepository = adminPatientRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<PatientResponseDto> getAll1(
            Pageable pageable
    ) {
        Page<PatientResponseDto> result = adminPatientRepository.findAll1(
                 pageable
        );


        return result;
    }











    public PatientResponseDto add (PatientRequestDto dto) {
        // Create a new entity from the DTO
//        if (adminPatientRepository.findByName(dto.getName()) != null) {
//            throw new DuplicateNameException("An AdminArTeams with the name '" + dto.getName() + "' already exists.");
//        }
//        if(dto.getName()==null|| dto.getName().trim()=="" || dto.getCreatedBy() == null){
//            throw new MandatoryFieldException("mandatory fields must not be empty or null");
//        }



        Patient obj = new Patient();

        // Set the properties of the template from the DTO
        obj.setName(dto.getName());


        // Save user ID in the created_by field
        if (dto.getCreatedBy() != null) {
            obj.setCreatedBy(dto.getCreatedBy());
        }




        // Save the template to the database
        Patient savedTemplate = adminPatientRepository.save(obj);

        // Convert the saved entity to response DTO
        return new PatientResponseDto(savedTemplate);
    }















}