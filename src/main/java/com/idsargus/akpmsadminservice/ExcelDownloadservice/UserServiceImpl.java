package com.idsargus.akpmsadminservice.ExcelDownloadservice;

 import com.idsargus.akpmsadminservice.ExcelDownloadDto.UserDTO;
 import com.idsargus.akpmsadminservice.Mvc.Entities.AdminUserMvc;
 import com.idsargus.akpmsadminservice.Mvc.Repository.AdminUserRepository;
 import com.idsargus.akpmsadminservice.entity.User;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AdminUserRepository userDataRestRepository;

    @Transactional
    @Override
    public List<UserDTO> getExcelDownload(
            String query,
            Boolean enabled,
            Integer role,
            List<Integer> departmentIds,
            Boolean deleted
    ) {

        // Ensure the repository returns a list of UserEntity
        List<AdminUserMvc> excelDownloadResponse = userDataRestRepository.fetchExcelDownload(query, enabled,
                role,
                departmentIds, deleted);

        if (excelDownloadResponse == null || excelDownloadResponse.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if the result is null or empty
        }

        // Map UserEntity to UserDTO using the correct method reference
        return excelDownloadResponse.stream()
                .map(UserDTO::toDTO)  // This should work correctly with UserEntity
                .limit(2000) // Adjust as needed
                .collect(Collectors.toList());
    }
}
