package ru.mityushin.jobfinder.server.model;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "PERSON")
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "UUID", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DELETED", nullable = false)
    private Boolean deleted;

    @Column(name = "LOCKED", nullable = false)
    private Boolean locked;

    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled;

    @Column(name = "EXPIRE")
    private ZonedDateTime expire;

    @Column(name = "CREDENTIALS_EXPIRE")
    private ZonedDateTime credentialsExpire;
}
