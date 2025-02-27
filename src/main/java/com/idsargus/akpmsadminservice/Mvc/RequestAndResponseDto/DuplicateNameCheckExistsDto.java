package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

public class DuplicateNameCheckExistsDto {
    String name ;

    public DuplicateNameCheckExistsDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
