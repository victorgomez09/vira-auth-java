package com.virasoftware.docservice.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.common.errors.ResourceNotFoundException;
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
		return spaceUserRepository.findAllByUser(userId).stream().map(SpaceUser::getSpace).collect(Collectors.toList());
	}
	
	public Space findSpaceById(String spaceId, String userId) {
		Space space = spaceRepository.findById(spaceId).orElseThrow(() -> new ResourceNotFoundException("Space not found"));
		
		spaceUserRepository.findBySpaceAndUser(space, userId).orElseThrow(() -> new UnauthorizedException("User dont have access to this space"));
		
		return space;
	}

	public Space createSpace(Space requestData, String userId) {
		if (spaceRepository.findByCode(requestData.getCode()).isPresent()
				|| spaceRepository.findByName(requestData.getName()).isPresent()) {
			throw new ConflictException("Space name or code already exists");
		}

		Space spaceCreated = spaceRepository.save(requestData);

		SpaceUser spaceUser = new SpaceUser();
		spaceUser.setPermission(Permission.WRITE);
		spaceUser.setUser(userId);
		spaceUser.setSpace(spaceCreated);
		spaceUserRepository.save(spaceUser);

		return spaceCreated;
	}

	public Space updateSpace(Space requestData, String userId) {
		if (spaceRepository.findByCode(requestData.getCode()).isEmpty()
				|| spaceRepository.findByName(requestData.getName()).isEmpty()) {
			throw new NotFoundException("Space not found");
		}
		
		Space space = spaceRepository.findById(requestData.getId()).orElseThrow();
		SpaceUser spaceUser = spaceUserRepository.findBySpaceAndUser(space, userId).orElseThrow(() -> new PermissionsException("User have not permissions for update"));
		if (spaceUser.getPermission() == Permission.READ) {
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
		
		Space space = spaceRepository.findById(spaceId).get();
		SpaceUser spaceUser = spaceUserRepository.findBySpaceAndUser(space, userId).orElseThrow(() -> new PermissionsException("User have not permissions for update"));
		if (spaceUser.getPermission() == Permission.READ) {
			throw new PermissionsException("User have not permissions for update");
		}
		
		spaceRepository.delete(space);
	}
}
