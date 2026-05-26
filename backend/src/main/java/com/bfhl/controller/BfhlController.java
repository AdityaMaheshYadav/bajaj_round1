package com.bfhl.controller;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import com.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> processData(@Valid @RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bfhl")
    public ResponseEntity<Object> getOperationCode() {
        return ResponseEntity.ok(java.util.Map.of("operation_code", 1));
    }

    @GetMapping("/health")
    public ResponseEntity<Object> checkHealth() {
        return ResponseEntity.ok(java.util.Map.of(
                "status", "UP",
                "is_success", true,
                "timestamp", java.time.Instant.now().toString()
        ));
    }
}
