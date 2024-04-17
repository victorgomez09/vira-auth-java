package com.virasoftware.docservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virasoftware.docservice.domains.entities.Space;
import com.virasoftware.docservice.domains.entities.SpaceUser;

@Repository
public interface SpaceUserRepository extends JpaRepository<SpaceUser, String> {
	List<SpaceUser> findAllByUser(String userId);
	List<SpaceUser> findAllBySpace(Space space);
	Optional<SpaceUser> findBySpaceAndUser(Space space, String userId);
}
