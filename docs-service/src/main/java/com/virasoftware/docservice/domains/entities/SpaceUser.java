package com.virasoftware.docservice.domains.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import com.virasoftware.docservice.enums.Permission;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "space_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpaceUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(name = "user_id")
    private String user;
    
    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;
    
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    private Permission permission; 
    
    @CreationTimestamp
    @Column(name = "creation_date")
    private Instant creationDate;
    
    @CreationTimestamp
    @Column(name = "modification_date")
    private Instant modificationDate;
}
