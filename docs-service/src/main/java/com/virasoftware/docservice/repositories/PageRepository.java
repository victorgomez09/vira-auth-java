package com.virasoftware.docservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virasoftware.docservice.domains.entities.Page;
import com.virasoftware.docservice.domains.entities.Space;

@Repository
public interface PageRepository extends JpaRepository<Page, String> {

	List<Page> findBySpace(Space space);
	
}
