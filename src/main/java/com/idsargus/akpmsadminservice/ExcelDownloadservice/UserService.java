package com.idsargus.akpmsadminservice.ExcelDownloadservice;




import com.idsargus.akpmsadminservice.ExcelDownloadDto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getExcelDownload(
            String query,
            Boolean enabled,
            Integer role,
            List<Integer> departmentIds,
            Boolean deleted
    );
}
