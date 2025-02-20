package com.idsargus.akpmsadminservice.Mvc.Service;

import com.idsargus.akpmsadminservice.Mvc.Repository.ArSourceRepository;
import com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto.ArSourceResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArSourceService {


    private final ArSourceRepository arSourceRepository;

    public ArSourceService(ArSourceRepository arSourceRepository) {
        this.arSourceRepository = arSourceRepository;
    }



    @Transactional(readOnly = true)
    public List<ArSourceResponseDto> getAll1(
    ) {
        List<ArSourceResponseDto> result = arSourceRepository.findAll1(
        );

        return result;
    }


}