package com.virasoftware.docservice.domains.entities;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "spaces")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Space {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String code;
    
    private String description;
    
    private String owner;
    
    @OneToMany(mappedBy = "space", fetch = FetchType.EAGER)
    private List<SpaceUser> users;
    
    @OneToMany(mappedBy = "space", fetch = FetchType.EAGER)
    private List<Page> pages;
    
    @CreationTimestamp
    @Column(name = "creation_date")
    private Instant creationDate;
    
    @UpdateTimestamp
    @Column(name = "modification_date")
    private Instant modificationDate;

}
