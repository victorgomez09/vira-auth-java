package com.virasoftware.authservice.domains.entities;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "refresh_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshToken {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ref_token_id_seq")
    @SequenceGenerator(name = "ref_token_id_seq", sequenceName = "ref_token_id_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private Instant expiration;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public boolean isExpired() {
        return this.getExpiration().isBefore(ZonedDateTime.now(ZoneId.systemDefault()).toInstant());
    }

}
