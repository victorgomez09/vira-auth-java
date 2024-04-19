package com.virasoftware.authservice.domains.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
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
<<<<<<< HEAD:auth-service/src/main/java/com/virasoftware/authservice/domains/entities/User.java
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "activation_code")
	private String activationCode;

	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", nullable = false))
	private Set<Role> roles;

	@OneToOne(mappedBy = "user", cascade = CascadeType.DETACH)
	private RefreshToken refreshToken;
=======
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
>>>>>>> a6d1929 (code refactor):auth-service/src/main/java/com/virasoftware/authservice/domains/entities/AuthUser.java

}