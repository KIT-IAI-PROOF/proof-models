/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_ID_NAME;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * Represents an ConnectionDao entity used in graph-based workflows. The ConnectionDao connects source and target nodes,
 * with additional properties to define behavior, such as animation and algorithms.
 * The entity supports persistence and auditing. It is mapped to a database table named "CONNECTION"
 * and is associated with points that define its geometry or path.
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "connection")
public class ConnectionDao extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 11247382472343L;

    /**
     * Represents the unique identifier for an entity.
     * This field is used as the primary key and is a required property.
     * It can be serialized to and deserialized from JSON using the key "id".
     * The value may be null in certain scenarios but is typically mandatory.
     */
    @Id
    @Nullable
    @Include
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private String id;

    /**
     * Represents the type attribute of an ConnectionDao entity.
     * This field indicates the type of the connection, allowing customization or further classification.
     * It is optional and can be null. Serialized as "type" in JSON.
     */
    @Nullable
    @Include
    @Column(name = "type")
    private String type;

    @Nullable
    @Include
    @Column(name = "connection_type")
    private String connectionType;

    /**
     * Represents the source of the data.
     * This field is optional and can be null. It is mapped to a column named
     * "source" in the database. When serialized to JSON, it includes the
     * corresponding "source" property.
     */
    @Nullable
    @Include
    @Column(name = "source")
    private String source;

    /**
     * Represents the handle or identifier for the source within a given context.
     * This field is nullable and may not always be present.
     * It is serialized and deserialized via JSON for external communication.
     * The value is stored in the database column "output".
     */
    @Nullable
    @Include
    @Column(name = "output")
    private String output;

    /**
     * Represents the target value as a String.
     * This field is optionally included in JSON serialization and deserialization.
     * It is mapped to the "target" column in the database and can contain null values.
     */
    @Nullable
    @Include
    @Column(name = "target")
    private String target;

    /**
     * Represents the identifier or reference handle associated with a specific target.
     * This field is nullable and can be excluded during serialization if not present.
     * It is mapped to the "input" column in the database and serialized/deserialized
     * as "input" in JSON format.
     */
    @Nullable
    @Include
    @Column(name = "input")
    private String input;

    /**
     * Indicates whether the associated entity or element is animated.
     * This variable is a Boolean that can hold either {@code true} if animated,
     * {@code false} if not animated, or {@code null} if the animation state is unspecified.
     * The variable is mapped to the database column "animated" which allows null values.
     * It is also serialized and deserialized into JSON as the property "animated".
     */
    @Nullable
    @Include
    @Column(name = "animated")
    private Boolean animated;

    /**
     * Represents the algorithm used in a specific operation or configuration.
     * The value for this variable is stored in the "algorithm" column in the database.
     */
    @Nullable
    @Include
    @Column(name = "algorithm")
    private String algorithm;

    /**
     * Represents a list of points associated with this ConnectionDao entity.
     * Points define the geometric structure or path of the connection in a graph-based workflow.
     * The points are stored as a collection and are managed as a one-to-many relationship
     * with the PointDao entity. Each point is associated with this connection and mapped through
     * the "connection" field in the PointDao entity. Serialized as "points" in JSON representation.
     */
    @Include
    @Nullable
    @OneToMany(targetEntity = PointDao.class, fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "connection_id", referencedColumnName = "id")
    private List<PointDao> points;

    /**
     * Callback method annotated with @PrePersist to automatically populate
     * the `id` field with a randomly generated UUID if it is null before
     * persisting the entity.
     * <p>
     * This ensures that the entity always has a unique identifier assigned
     * at the moment of persistence in the database.
     */
    @PrePersist
    public void onCreate() {
        if (isNull(this.id)) {
            this.id = randomUUID().toString();
        }
    }

}
