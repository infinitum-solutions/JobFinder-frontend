package ru.mityushin.jobfinder.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "ORGANIZATION")
public class Organization implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID", nullable = false, unique = true)
    private UUID uuid;

    private UserInfo owner;

    @Column(name = "URL", length = 64, nullable = false, unique = true)
    private String url;

    @Column(name = "TITLE", length = 255, nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ORGANIZATION_MEMBERS",
            joinColumns = {@JoinColumn(name = "ORGANIZATION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "MEMBER_ID")})
    private Set<UserInfo> members;

    @ManyToMany
    @JoinTable(name = "ORGANIZATION_SUBSCRIBERS",
            joinColumns = {@JoinColumn(name = "ORGANIZATION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SUBSCRIBER_ID")})
    private Set<UserInfo> subscribers;

    @Column(name = "DELETED", nullable = false)
    private Boolean deleted;
}
