package com.idsargus.akpmsadminservice.Mvc.Controller;

import com.idsargus.akpmsadminservice.Mvc.Service.PayloadService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payload")
public class PayloadController {
    private final PayloadService payloadService;

    public PayloadController(PayloadService payloadService) {
        this.payloadService = payloadService;
    }

    // Endpoint to save payload
    @PostMapping("/{apiName}")
    public String savePayload(@PathVariable String apiName, @RequestBody Map<String, Object> payload) {
        payloadService.savePayload(apiName, payload);
        return "Payload for " + apiName + " saved successfully!";
    }

    // Endpoint to get last saved payload
    @GetMapping("/{apiName}")
    public Object getPayload(@PathVariable String apiName) {
        return payloadService.getPayload(apiName);
    }
}
