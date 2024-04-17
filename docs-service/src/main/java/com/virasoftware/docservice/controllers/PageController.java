package com.virasoftware.docservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virasoftware.common.aspects.ValidateErrors;
import com.virasoftware.docservice.domains.dtos.PageDto;
import com.virasoftware.docservice.services.PageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/doc/page")
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Page API", description = "Interaction with pages")
@SecurityRequirement(name = "Bearer Authentication")
public class PageController {

	private final PageService pageService;

	@Operation(summary = "Get all pages from space", description = "Get all pages from space")
	@PostMapping
	@ValidateErrors
	public ResponseEntity<List<PageDto>> findAllPagesBySpace(@RequestHeader("X-UserId") String userId,
			@PathVariable("space") String spaceId, BindingResult result) {
		return ResponseEntity.ok(pageService.findAllPagesBySpace(spaceId, userId));
	}

	@Operation(summary = "Get page by id", description = "Get all pages from space")
	@PostMapping
	@ValidateErrors
	public ResponseEntity<PageDto> findPageById(@RequestHeader("X-UserId") String userId,
			@PathVariable("page") String pageId, BindingResult result) {
		return ResponseEntity.ok(pageService.findPageById(pageId, userId));
	}

	@Operation(summary = "Create a new page", description = "Create new page")
	@PostMapping
	@ValidateErrors
	public ResponseEntity<PageDto> createPage(@RequestBody @Valid PageDto requestDto,
			@Parameter(hidden = true) @RequestHeader("X-UserId") String userId, BindingResult result) {
		return ResponseEntity.ok(pageService.createPage(requestDto, userId));
	}

	@Operation(summary = "Update a page", description = "Update a page")
	@PutMapping
	@ValidateErrors
	public ResponseEntity<PageDto> updatePage(@RequestBody @Valid PageDto requestDto,
			@Parameter(hidden = true) @RequestHeader("X-UserId") String userId, BindingResult result) {
		return ResponseEntity.ok(pageService.updatePage(requestDto, userId));
	}
	
	@Operation(summary = "Delete a page", description = "Delete a page")
	@DeleteMapping
	@ValidateErrors
	public ResponseEntity<Void> deletePage(@PathVariable("page") @Valid String pageId,
			@Parameter(hidden = true) @RequestHeader("X-UserId") String userId, BindingResult result) {
		pageService.deletePage(pageId, userId);
		
		return ResponseEntity.ok().build();
	}
	
}