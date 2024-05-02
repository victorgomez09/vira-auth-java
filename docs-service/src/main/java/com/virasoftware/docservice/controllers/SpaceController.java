package com.virasoftware.docservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virasoftware.common.aspects.ValidateErrors;
import com.virasoftware.docservice.domains.dtos.SpaceDto;
import com.virasoftware.docservice.mappers.SpaceMapper;
import com.virasoftware.docservice.services.SpaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/doc/space")
@RequiredArgsConstructor
@Tag(name = "Space API", description = "Interaction with spaces")
@SecurityRequirement(name = "Bearer Authentication")
public class SpaceController {

	private final SpaceService service;
	private final SpaceMapper mapper;

	@Operation(summary = "Get all spaces from user", description = "Get all spaces from user")
	@GetMapping
	@ValidateErrors
	public ResponseEntity<List<SpaceDto>> findAllSpacesByUser(@RequestHeader("X-User-Id") String userId,
			BindingResult result) {
		return ResponseEntity.ok(service.findAllSpacesByUser(userId).stream().map(mapper::toDto).toList());
	}

	@Operation(summary = "Get space with id", description = "Get space with id")
	@GetMapping("/{spaceId}")
	@ValidateErrors
	public ResponseEntity<SpaceDto> findSpaceById(@RequestHeader("X-User-Id") String userId,
			@PathVariable("spaceId") String spaceId, BindingResult result) {
		return ResponseEntity.ok(mapper.toDto(service.findSpaceById(spaceId, userId)));
	}

	@Operation(summary = "Create a new space", description = "Create new space")
	@PostMapping
	@ValidateErrors
	public ResponseEntity<SpaceDto> createSpace(@RequestBody @Valid SpaceDto requestDto,
			@Parameter(hidden = true) @RequestHeader("X-User-Id") String userId, BindingResult result) {
		return ResponseEntity.ok(mapper.toDto(service.createSpace(mapper.toEntity(requestDto), userId)));
	}

	@Operation(summary = "Update a space", description = "Update a space")
	@PutMapping
	@ValidateErrors
	public ResponseEntity<SpaceDto> updateSpace(@RequestBody @Valid SpaceDto requestDto,
			@Parameter(hidden = true) @RequestHeader("X-User-Id") String userId, BindingResult result) {
		return ResponseEntity.ok(mapper.toDto(service.updateSpace(mapper.toEntity(requestDto), userId)));
	}
}