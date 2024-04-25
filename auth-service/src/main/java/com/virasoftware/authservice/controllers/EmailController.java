package com.virasoftware.authservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virasoftware.authservice.dtos.EmailDto;
import com.virasoftware.authservice.services.EmailService;
import com.virasoftware.common.aspects.ValidateErrors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/email")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Email API", description = "Update and verify email")
public class EmailController {
    private final EmailService emailService;

    @Operation(summary = "Update email", description = "Update email")
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping
    @ValidateErrors
    public ResponseEntity<String> updateEmail(
            @RequestBody @Valid EmailDto requestDto,
            BindingResult result,
            @Parameter(hidden = true) @RequestHeader("X-User-Id") Long userId
    ) {
        emailService.updateEmail(userId, requestDto);
        
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Verify email", description = "Verify email")
    @GetMapping
    public ResponseEntity<String> verifyEmail(
            @RequestParam("code") @NotBlank String activationCode
    ) {
        emailService.verifyEmail(activationCode);
        
        return ResponseEntity.noContent().build();
    }

}
