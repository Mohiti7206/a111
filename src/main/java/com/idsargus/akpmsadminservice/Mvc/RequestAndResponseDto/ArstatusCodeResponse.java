package com.idsargus.akpmsadminservice.Mvc.RequestAndResponseDto;

public class ArstatusCodeResponse {

    private  String id;
    private  String name;

    public ArstatusCodeResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
