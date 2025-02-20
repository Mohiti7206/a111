package com.idsargus.akpmsadminservice.Mvc.Controller;


import com.idsargus.akpmsadminservice.Mvc.Service.ArSourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/adminapi/arsources")
public class ArSourceController {

    private final ArSourceService arSourceService;

    public ArSourceController(ArSourceService arSourceService) {
        this.arSourceService = arSourceService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getArSources() {
        return ResponseEntity.ok(arSourceService.getAll1());
    }
}