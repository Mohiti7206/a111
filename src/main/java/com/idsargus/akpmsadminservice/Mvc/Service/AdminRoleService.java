package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Repository.DoctorRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.RoleDataRestRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminDoctorResponse;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.AdminRoleResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AdminRoleService {



    private final RoleDataRestRepository roleDataRestRepository;

    public AdminRoleService(RoleDataRestRepository roleDataRestRepository) {
        this.roleDataRestRepository = roleDataRestRepository;
    }

    @Transactional(readOnly = true)
    public List<AdminRoleResponse> findByAllEnabled(
    ) {
        List<AdminRoleResponse> result = roleDataRestRepository.findAll1();
        return result;
    }

}
