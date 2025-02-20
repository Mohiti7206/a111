package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.Service.ArStatusCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/adminapi/arstatuscodes")
public class ArStatusCodeController {

    private final ArStatusCodeService arStatusCodeService;

    public ArStatusCodeController(ArStatusCodeService arStatusCodeService) {
        this.arStatusCodeService = arStatusCodeService;
    }

    @GetMapping("/search/all")
    public ResponseEntity<?> getArSources() {
        return ResponseEntity.ok(arStatusCodeService.getAll1());
    }
}