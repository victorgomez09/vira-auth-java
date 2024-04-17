package com.virasoftware.docservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virasoftware.common.aspects.ValidateErrors;
import com.virasoftware.docservice.domains.dtos.SpaceDto;
import com.virasoftware.docservice.domains.dtos.SpaceResponseDto;
import com.virasoftware.docservice.services.SpaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/doc/space")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Space API", description = "Interaction with spaces")
@SecurityRequirement(name = "Bearer Authentication")
public class SpaceController {

	private final SpaceService spaceService;
	
	@Operation(summary = "Get all spaces from user", description = "Get all spaces from user")
	@PostMapping
	@ValidateErrors
	public ResponseEntity<List<SpaceResponseDto>> findAllSpacesByUser(@RequestHeader("X-UserId") String userId, BindingResult result) {
		return ResponseEntity.ok(spaceService.findAllSpacesByUser(userId));
	}

	@Operation(summary = "Create a new space", description = "Create new space")
	@PostMapping
	@ValidateErrors
	public ResponseEntity<SpaceDto> createSpace(@RequestBody @Valid SpaceDto requestDto,
			@Parameter(hidden = true) @RequestHeader("X-UserId") String userId, BindingResult result) {
		return ResponseEntity.ok(spaceService.createSpace(requestDto, userId));
	}
	
	@Operation(summary = "Update a space", description = "Update a space")
	@PostMapping
	@ValidateErrors
	public ResponseEntity<SpaceDto> updateSpace(@RequestBody @Valid SpaceDto requestDto,
			@Parameter(hidden = true) @RequestHeader("X-UserId") String userId, BindingResult result) {
		return ResponseEntity.ok(spaceService.updateSpace(requestDto, userId));
	}	
}