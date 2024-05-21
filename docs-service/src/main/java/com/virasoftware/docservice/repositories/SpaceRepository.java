package com.virasoftware.docservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virasoftware.docservice.domains.entities.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, String> {
	@Query("FROM Space s join s.users u ON u.user = :userId")
	List<Space> findAllByUser(@Param("userId") String userId);

	@Query("FROM Space s join s.users u ON u.user = :userId WHERE s.id = :spaceId")
	Optional<Space> findByIdAndUser(@Param("spaceId") String id, @Param("userId") String userId);

	Optional<Space> findByCode(String code);

	Optional<Space> findByName(String name);
}
