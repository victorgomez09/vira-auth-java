package com.virasoftware.docservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virasoftware.docservice.domains.entities.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, String> {
	Optional<Space> findByCode(String code);
	Optional<Space> findByName(String name);
}
