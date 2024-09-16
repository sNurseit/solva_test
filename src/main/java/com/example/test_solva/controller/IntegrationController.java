package com.example.test_solva.controller;

import com.example.test_solva.model.dto.ErrorMessage;
import com.example.test_solva.model.entity.RateEntity;
import com.example.test_solva.service.IntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/integration")
@RequiredArgsConstructor
@Tag(name = "Integration", description = "Operations related to exchange rate integration")
public class IntegrationController {

    private final IntegrationService integrationService;

    @Operation(summary = "Retrieve exchange rates", description = "Fetches exchange rates between the specified dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exchange rates retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RateEntity[].class))),
            @ApiResponse(responseCode = "500", description = "Exchange rates retrieved failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping
    public ResponseEntity<List<RateEntity>> create(
            @Parameter(description = "Start date in the format yyyy-MM-dd (must not be before 2024-07-01)", example = "2024-08-01")
            @RequestParam(name = "startDate", required = false) LocalDate startDate,

            @Parameter(description = "End date in the format yyyy-MM-dd (must not be before 2024-07-01)", example = "2024-08-10")
            @RequestParam(name = "endDate", required = false) LocalDate endDate
    ){
        return ResponseEntity.ok(integrationService.getExchangeRates(startDate, endDate));
    }
}
