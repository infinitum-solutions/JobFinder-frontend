package ru.mityushin.jobfinder.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.mityushin.jobfinder.server.util.enums.Sex;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@org.hibernate.annotations.Entity
@Table(name = "USER_INFO")
public class UserInfo implements Entity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "UUID", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "USERNAME", length = 64, nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 64, nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USERINFO_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;

    @Column(name = "URL", length = 30, nullable = false, unique = true)
    private String url;

    @Column(name = "FIRST_NAME", length = 30, nullable = false)
    private String firstName;

    @Column(name = "SECOND_NAME", length = 40)
    private String secondName;

    @Column(name = "LAST_NAME", length = 40, nullable = false)
    private String lastName;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Publication> publications;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "subscribers")
    private Set<Organization> subscribedOrganizations;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "members")
    private Set<Organization> memberOrganizations;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

}
