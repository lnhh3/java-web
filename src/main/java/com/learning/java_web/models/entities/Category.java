package com.learning.java_web.models.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.learning.java_web.commons.enums.SystemStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "[category]")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private int level;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SystemStatus status;
}
