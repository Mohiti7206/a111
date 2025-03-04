package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Entities.*;
import com.idsargus.akpmsadminservice.Mvc.Exception.DuplicateNameException;
import com.idsargus.akpmsadminservice.Mvc.Exception.MandatoryFieldException;
import com.idsargus.akpmsadminservice.Mvc.Repository.*;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminUserService {


    private final AdminUserRepository adminUserRepository;
     private final AdminEmailTemplateRepository emailTemplateRepository;

    public AdminUserService(AdminUserRepository adminUserRepository, AdminEmailTemplateRepository emailTemplateRepository) {
        this.adminUserRepository = adminUserRepository;
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @Autowired
    private AdminUserRepository userRepository;

    @Autowired
    private AdmnPermissionRepository admnPermissionRepository;

    @Autowired
    private AdminDepartmentRepository adminDepartmentRepository;







//    public AdminUserMvc getById(Integer id){
//        return adminUserRepository.findByUserId(id);
//    }

    @Transactional
    public AdminUserResponseDto getById(Integer id ) {
         AdminUserMvc existingEntity = adminUserRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("User with provided ID not found."));
         return convertToDTO(existingEntity);
    }
















    @Transactional(readOnly = true)
    public Page<AdminUserResponseDto> getAll1(
            Boolean enabled,
            Boolean deleted,
            String query,
            Pageable pageable

    ) {

        Page<AdminUserResponseDto> result = adminUserRepository.findAll1(enabled,deleted,query,pageable);


        System.out.println("hello 1 ");
        result.forEach(user -> {
            List<String> permissions =  admnPermissionRepository.findByUserId(user.getId());
            List<Integer> departments = adminDepartmentRepository.findByUserId(user.getId());
            List<Integer> etplates =    adminDepartmentRepository.findByUserIdEt(user.getId());
//            user.setPermissionIdList(permissions);
            user.setPermissionIds(permissions);
            user.setDepartmentIds(departments);
            user.setEmailTemplateIds(etplates);
        });

        return result;
    }








    @Transactional
    public AdminUserResponseDto updateTemplateActivationStatus(Integer id, Boolean enabled) {
        // Fetch the existing entity from the database
        AdminUserMvc existingEntity = adminUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with provided ID not found."));

        // Update only the 'enabled' field and keep all other fields unchanged
        existingEntity.setEnabled(enabled);

        // Save the updated entity back to the database
        AdminUserMvc  updatedEntity = adminUserRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }






    private AdminUserResponseDto convertToDTO(AdminUserMvc entity) {
        AdminUserResponseDto dto = new AdminUserResponseDto();
        dto.setId(entity.getId());
//        dto.setName(entity.getFirstName() +" "+entity.getLastName());
        dto.setFirstName( entity.getFirstName());
        dto.setLastName( entity.getLastName());
        dto.setEnabled(entity.getEnabled());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setModifiedOn(entity.getModifiedOn());
        dto.setEmail(entity.getEmail());
        dto.setContact(entity.getContact());
        dto.setAddress(entity.getAddress());
        dto.setUserRoleId(entity.getRole().getId());
        Set<AdminDepartmentEntityMvc> dids = entity.getDepartments();
        dto.setDepartmentIds(dids.stream().map(AdminDepartmentEntityMvc::getId).collect(Collectors.toList()));


        Set<AdminPermissionEntityMvc> pids = entity.getPermissions();
        dto.setPermissionIds(pids.stream().map(AdminPermissionEntityMvc::getId).collect(Collectors.toList()));

        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " " + entity.getCreatedBy().getLastName(): null);
        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " " + entity.getModifiedBy().getLastName(): null);
        return dto;
    }



    public AdminUserResponseDto addUser(AdminUserRequestDto dto) {
        // Create a new entity from the DTO
        System.out.println("3 ");
        if (adminUserRepository.findByFullName(dto.getFirstName()  +" " +dto.getLastName()) != null) {
            throw new DuplicateNameException("An User with the name '" + dto.getFirstName() + "' already exists.");
        }


        String email = dto.getEmail();
        if (email != null && adminUserRepository.findByEmail(email) != null) {
            throw new DuplicateNameException("An user with the email '" + email + "' already exists.");
        }



        System.out.println("4 ");
        AdminUserMvc user = new AdminUserMvc();

        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) {
            throw new MandatoryFieldException("First Name is required.");
        }
        System.out.println("5 ");
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new MandatoryFieldException("Email is required.");
        }
        if (dto.getRole() == null) {
            throw new MandatoryFieldException("Role is required.");
        }



        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
//        if (dto.getRole() != null) {
//            user.setRole(dto.getRole());
//        }

        user.setLastName(dto.getLastName());
        user.setEnabled(dto.getEnabled());
        user.setDeleted(dto.getDeleted());




        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(dto.getPassword()));
//        user.setPassword(dto.getPassword());






        user.setContact(dto.getContact());
        user.setAddress(dto.getAddress());
        user.setLocation(dto.getLocation());


         if (dto.getCreatedBy() != null) {
             user.setCreatedBy(dto.getCreatedBy());
        }

         if (dto.getArTeam() != null) {
             user.setArTeam(dto.getArTeam());
        }
        AdminUserMvc user1 = adminUserRepository.save(user);

        return new AdminUserResponseDto(user1);
    }

























    @Transactional
    public AdminUserResponseDto updateuser(AdminUserRequestDto adminUserRequestDto, Integer id) {
        // Fetch the existing entity from the database

//        System.out.println("id =="+id);
        AdminUserMvc existingEntity = adminUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with provided ID not found."));

        AdminUserMvc existingEntity2 =  adminUserRepository.findByFullName(adminUserRequestDto.getFirstName()  +" " +adminUserRequestDto.getLastName());
        if (existingEntity2 != null && !existingEntity2.getId().equals(id)) {
            throw new DuplicateNameException("An User with the name '" + adminUserRequestDto.getFirstName() + "' already exists.");
        }


        // Update only non-null fields
        existingEntity.setFirstName(adminUserRequestDto.getFirstName());
        existingEntity.setLastName(adminUserRequestDto.getLastName());
        existingEntity.setEnabled(adminUserRequestDto.getEnabled());
          existingEntity.setDeleted(adminUserRequestDto.getDeleted());
          existingEntity.setEmail(adminUserRequestDto.getEmail());
         existingEntity.setRole(adminUserRequestDto.getRole());
          existingEntity.setArTeam(adminUserRequestDto.getArTeam());
         existingEntity.setLocation(adminUserRequestDto.getLocation());
         existingEntity.setAddress(adminUserRequestDto.getAddress());
         existingEntity.setPassword(adminUserRequestDto.getPassword());
         existingEntity.setContact(adminUserRequestDto.getContact());





//        System.out.println("3=========================================");

        // Fetch the 'modifiedBy' user from the database and set it
        if (adminUserRequestDto.getModifiedBy() != null && adminUserRequestDto.getModifiedBy().getId() != null) {
            AdminUserMvc modifiedByUser = userRepository.findById(adminUserRequestDto.getModifiedBy().getId())
                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
            existingEntity.setModifiedBy(modifiedByUser);
        }
//        System.out.println("4=========================================");

        // Save the updated entity
        AdminUserMvc updatedEntity = adminUserRepository.save(existingEntity);
//        System.out.println("5=========================================");

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }




  @Transactional
    public AdminUserResponseDto updateuserDepartments(
            List<Integer> permissionIds,
            Integer userId) {
        // Fetch the existing entity from the database

//        System.out.println("id =="+id);
        AdminUserMvc existingEntity = adminUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with provided ID not found."));

//        System.out.println("id =="+id);



//          adminUserRepository.deleteUserDepartments(userId);



//        System.out.println("3=========================================");
//
//        // Fetch the 'modifiedBy' user from the database and set it
//        if (adminUserRequestDto.getModifiedBy() != null && adminUserRequestDto.getModifiedBy().getId() != null) {
//            User modifiedByUser = userRepository.findById(adminUserRequestDto.getModifiedBy().getId())
//                    .orElseThrow(() -> new RuntimeException("ModifiedBy user not found."));
//            existingEntity.setModifiedBy(modifiedByUser);
//        }
//        System.out.println("4=========================================");

        // Save the updated entity
        AdminUserMvc updatedEntity = adminUserRepository.save(existingEntity);
//        System.out.println("5=========================================");

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }












//    private AdminUserResponseDto convertToDTO(AdminUserMvc entity) {
//        AdminUserResponseDto dto = new AdminUserResponseDto();
//        dto.setId(entity.getId());
//        dto.setName(entity.getFirstName() +" "+ entity.getLastName());
//        dto.setEnabled(entity.getEnabled());
//        dto.setCreatedOn(entity.getCreatedOn());
//        dto.setModifiedOn(entity.getModifiedOn());
//        dto.setCreatedByUserName(entity.getCreatedBy() != null ? entity.getCreatedBy().getFirstName() + " " + entity.getCreatedBy().getLastName(): null);
//        dto.setModifiedByUserName(entity.getModifiedBy() != null ? entity.getModifiedBy().getFirstName() + " " + entity.getModifiedBy().getLastName(): null);
//        return dto;
//    }







































    @Transactional
    public AdminUserResponseDto updateUserEmailTemplate(
            Set<Integer> emailTemplateIds,
            Integer userId) {

        // Fetch the existing user entity from the database
        AdminUserMvc existingEntity = adminUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with provided ID not found."));

        // Fetch the AdminEmailTemplateEntity objects from the database
        Set<AdminEmailTemplateEntity2> emailTemplates = new HashSet<>(emailTemplateRepository.findAllById(emailTemplateIds));

        // Assign the fetched entities to the user
        existingEntity.setEmailTemplates(emailTemplates);

        // Save the updated entity
        AdminUserMvc updatedEntity = adminUserRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }


//    PATCH http://localhost:5003/v1/adminapi/users/update/emailTemplates/100228
//    {
//        "emailTemplateIds": [1, 7]
//    }



    @Transactional
    public AdminUserResponseDto updateUserDepartment(
            Set<Integer> departmentIds,
            Integer userId) {

        // Fetch the existing user entity from the database
        AdminUserMvc existingEntity = adminUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with provided ID not found."));

        // Fetch the AdminEmailTemplateEntity objects from the database
        Set<AdminDepartmentEntityMvc> departments = new HashSet<>(adminDepartmentRepository.findAllById(departmentIds));

        // Assign the fetched entities to the user
        existingEntity.setDepartments(departments);

        // Save the updated entity
        AdminUserMvc updatedEntity = adminUserRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }

    @Transactional
    public AdminUserResponseDto updateUserPermission(
            Set<String> PermissionIds,
            Integer userId) {

        // Fetch the existing user entity from the database
        AdminUserMvc existingEntity = adminUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with provided ID not found."));

        // Fetch the AdminEmailTemplateEntity objects from the database
        Set<AdminPermissionEntityMvc> permissions = new HashSet<>(admnPermissionRepository.findAllById(PermissionIds));

        // Assign the fetched entities to the user
        existingEntity.setPermissions(permissions);

        // Save the updated entity
        AdminUserMvc updatedEntity = adminUserRepository.save(existingEntity);

        // Convert the updated entity to a DTO and return
        return convertToDTO(updatedEntity);
    }



















    @Transactional(readOnly = true)
    public List<AdminUserResponseDto> findByAllEnabled(
    ) {
        List<AdminUserResponseDto> result = adminUserRepository.findByAllEnabled();
        return result;
    }







    @Transactional(readOnly = true)
    public Boolean getbyname(DuplicateNameCheckExistsDto duplicateNameCheckExistsDto) {

        return adminUserRepository.findByEmail(duplicateNameCheckExistsDto.getName()) != null;
    }












}
