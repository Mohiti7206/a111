package com.idsargus.akpmsadminservice.Mvc.Service;



import com.idsargus.akpmsadminservice.Mvc.Repository.AdmnPermissionRepository;
 import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AdminPermissionsService {


    private final AdmnPermissionRepository admnPermissionRepository;

    public AdminPermissionsService(AdmnPermissionRepository admnPermissionRepository) {
        this.admnPermissionRepository = admnPermissionRepository;
    }



    @Transactional(readOnly = true)
    public List<AdminPermissionsResponseDto> getAll1(
     ) {
        List<AdminPermissionsResponseDto> result = admnPermissionRepository.findAll1();

        return result;
    }
















}

