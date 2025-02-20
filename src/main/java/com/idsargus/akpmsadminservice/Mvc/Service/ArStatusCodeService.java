package com.idsargus.akpmsadminservice.Mvc.Service;

//import com.idsargus.akpmsadminservice.Mvc.Repository.ArstatusCodeRepository;
import com.idsargus.akpmsadminservice.Mvc.Repository.ArStatusCodesRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArstatusCodeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArStatusCodeService {


    private final ArStatusCodesRepository  arstatusCodeRepository;

    public ArStatusCodeService(ArStatusCodesRepository arstatusCodeRepository) {
        this.arstatusCodeRepository = arstatusCodeRepository;
    }



    @Transactional(readOnly = true)
    public List<ArstatusCodeResponse> getAll1(
     ) {
        List<ArstatusCodeResponse> result = arstatusCodeRepository.findAll1(
          );

        return result;
    }


}