package com.virasoftware.docservice.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.virasoftware.common.exception.ConflictException;
import com.virasoftware.common.exception.UnauthorizedException;
import com.virasoftware.docservice.domains.entities.Space;
import com.virasoftware.docservice.domains.entities.SpaceUser;
import com.virasoftware.docservice.domains.exceptions.NotFoundException;
import com.virasoftware.docservice.domains.exceptions.PermissionsException;
import com.virasoftware.docservice.enums.Permission;
import com.virasoftware.docservice.repositories.SpaceRepository;
import com.virasoftware.docservice.repositories.SpaceUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpaceService {

	private final SpaceRepository spaceRepository;
	private final SpaceUserRepository spaceUserRepository;

	public List<Space> findAllSpacesByUser(String userId) {
		return spaceRepository.findAllByUser(userId);
	}

	public Space findSpaceById(String spaceId, String userId) {
		Space space = spaceRepository.findById(spaceId)
				.orElseThrow(() -> new NotFoundException("Space not found"));

		spaceRepository.findByIdAndUser(spaceId, userId)
				.orElseThrow(() -> new UnauthorizedException("User dont have access to this space"));

		return space;
	}

	public Space createSpace(Space requestData, String userId) {
		if (spaceRepository.findByCode(requestData.getCode()).isPresent()
				|| spaceRepository.findByName(requestData.getName()).isPresent()) {
			throw new ConflictException("Space name or code already exists");
		}

		List<SpaceUser> spaceUsers = new ArrayList<>();
		if (requestData.getUsers() != null && !requestData.getUsers().isEmpty()) {
			requestData.getUsers().forEach(u -> {
				SpaceUser spaceUser = new SpaceUser();
				spaceUser.setPermission(u.getPermission());
				spaceUser.setUser(u.getUser());
				spaceUsers.add(spaceUserRepository.save(spaceUser));
			});
		}

		requestData.setOwner(userId);
		requestData.setUsers(spaceUsers);

		return spaceRepository.save(requestData);
	}

	public Space updateSpace(Space requestData, String userId) {
		if (spaceRepository.findByCode(requestData.getCode()).isEmpty()
				|| spaceRepository.findByName(requestData.getName()).isEmpty()) {
			throw new NotFoundException("Space not found");
		}

		Space space = spaceRepository.findById(requestData.getId())
				.orElseThrow(() -> new PermissionsException("User have not permissions for update"));
		Optional<SpaceUser> spaceUser = space.getUsers().stream().filter(su -> su.getUser().equals(userId)).findFirst();
		if (!spaceUser.isPresent() || spaceUser.get().getPermission() == Permission.READ) {
			throw new PermissionsException("User have not permissions for update");
		}

		space.setName(requestData.getName());
		space.setName(requestData.getDescription());
		space.setModificationDate(Instant.now());

		return spaceRepository.save(space);
	}

	public void deleteSpace(String spaceId, String userId) {
		if (spaceRepository.findById(spaceId).isEmpty()) {
			throw new NotFoundException("Space not found");
		}

		Space space = spaceRepository.findById(spaceId)
				.orElseThrow(() -> new PermissionsException("User have not permissions for update"));
		Optional<SpaceUser> spaceUser = space.getUsers().stream().filter(su -> su.getUser().equals(userId)).findFirst();
		if (!spaceUser.isPresent() || spaceUser.get().getPermission() == Permission.READ) {
			throw new PermissionsException("User have not permissions for update");
		}

		spaceRepository.delete(space);
	}
}
