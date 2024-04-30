package com.virasoftware.docservice.domains.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Page {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    private String body;
    
    private Page parent;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "space_id")
    private Space space;
    
    private String treePos;
    
    private String owner;
    
    @CreationTimestamp
    @Column(name = "creation_date")
    private Instant creationDate;
    
    @UpdateTimestamp
    @Column(name = "modification_date")
    private Instant modificationDate;

}
