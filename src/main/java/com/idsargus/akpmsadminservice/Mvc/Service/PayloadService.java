package com.idsargus.akpmsadminservice.Mvc.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayloadService {
    private static final String FILE_PATH = "saved_payload.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Load saved payloads from file
    private Map<String, Object> loadPayloads() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new HashMap<String, Object>(); // Return empty map if file doesn't exist
    }

    // Save payload to file
    private void savePayloads(Map<String, Object> payloads) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), payloads);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save payload for a specific API
    public void savePayload(String apiName, Object payload) {
        Map<String, Object> payloads = loadPayloads();
        payloads.put(apiName, payload); // Store payload under API name
        savePayloads(payloads);
    }

    // Get payload for a specific API
    public Object getPayload(String apiName) {
        Map<String, Object> payloads = loadPayloads();
        Object result = payloads.get(apiName);
        return result != null ? result : Collections.emptyMap(); // Return payload or empty map
    }
}
