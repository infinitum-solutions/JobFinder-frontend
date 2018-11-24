package ru.mityushin.jobfinder.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@org.hibernate.annotations.Entity
@Table(name = "PUBLICATION")
public class Publication implements Entity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "UUID", nullable = false, unique = true)
    private UUID uuid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private UserInfo author;

    private Organization organization;

    @GeneratedValue
    @Column(name = "URL")
    private String url;

    @Column(name = "TITLE", length = 50, nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    @Column(name = "CONTENT", length = 255, nullable = false)
    private String content;

    @Column(name = "VISIBLE", nullable = false)
    private Boolean visible;

    @Column(name = "DELETED", nullable = false)
    private Boolean deleted;

}
