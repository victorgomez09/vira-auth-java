package com.virasoftware.authservice.domains.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_user_id_seq")
    @SequenceGenerator(name = "auth_user_id_seq", sequenceName = "auth_user_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "activation_code")
    private String activationCode;

    @OneToOne(mappedBy = "user", cascade = CascadeType.DETACH)
    private RefreshToken refreshToken;

}