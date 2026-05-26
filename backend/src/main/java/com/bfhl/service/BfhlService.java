package com.bfhl.service;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;

/**
 * Service interface for BFHL data processing operations.
 */
public interface BfhlService {

    /**
     * Processes the input data array and returns categorized results.
     *
     * @param request the request containing the data array
     * @return BfhlResponse with categorized data
     */
    BfhlResponse processData(BfhlRequest request);
}
