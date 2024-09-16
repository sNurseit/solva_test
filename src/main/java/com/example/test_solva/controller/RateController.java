package com.example.test_solva.controller;


import com.example.test_solva.model.dto.ErrorMessage;
import com.example.test_solva.model.dto.Rate;
import com.example.test_solva.model.entity.RateEntity;
import com.example.test_solva.service.RateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/rate")
@RequiredArgsConstructor
@Tag(name = "Rate Controller", description = "Operations related to exchange rates in the database")

public class RateController {
    private final RateService rateService;

    @Operation(summary = "Returns all exchange rates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exchange rates retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RateEntity[].class))),
            @ApiResponse(responseCode = "500", description = "Exchange rates retrieved failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(rateService.findAll());
    }

    @Operation(summary = "Returns exchange rates by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exchange rates retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RateEntity.class))),
            @ApiResponse(responseCode = "500", description = "Exchange rates retrieved failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{date}")
    public ResponseEntity<?> findById(
            @Parameter(description = "date in the format yyyy-MM-dd", example = "2024-08-01")
            @PathVariable("date")LocalDate date
    ){
        return ResponseEntity.ok(rateService.findById(date));
    }

    @Operation(summary = "Returns last exchange rates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exchange rates retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RateEntity[].class))),
            @ApiResponse(responseCode = "500", description = "Exchange rates retrieved failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/top")
    public ResponseEntity<?> findTop (
            @Parameter(description = "The size of return value", example = "10")
            @RequestParam(name = "count", defaultValue = "10") Integer count
    ){
        return ResponseEntity.ok(rateService.findTop(count));
    }

    @Operation(summary = "Update exchange rates by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exchange rates update successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RateEntity.class))),
            @ApiResponse(responseCode = "500", description = "Exchange rates update failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping("/{date}")
    public ResponseEntity<?> update(@PathVariable("date") LocalDate date, @RequestBody Rate rate){
        return ResponseEntity.ok(rateService.updateByDate(date, rate));
    }

    @DeleteMapping("/{date}")
    public void deleteByDate(@PathVariable("date") LocalDate date) {
        rateService.deleteByDate(date);
    }

}
