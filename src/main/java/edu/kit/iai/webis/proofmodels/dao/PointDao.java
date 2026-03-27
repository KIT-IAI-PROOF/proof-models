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

import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_ID_NAME;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * The PointDao class represents a two-dimensional point entity with additional attributes
 * such as uniqueness, previous state reference, and active status. This class is
 * annotated as a JPA entity and intended for persistence in a relational database.
 * It includes metadata for integration with JSON serialization/deserialization and
 * auditing functionalities.
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "point")
public class PointDao extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 23472384723435L;

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
     * Represents the x-coordinate of a point in a two-dimensional space.
     * This field is optional and can have a null value.
     * It is serialized and deserialized to/from JSON using the key "x".
     */
    @Nullable
    @Include
    @Column(name = "x")
    private Double x;

    /**
     * Represents the Y-coordinate of a point in a two-dimensional space.
     * It is stored as a nullable Double and can be serialized to or deserialized from JSON with the key "y".
     * This field can be optionally included in JSON responses or database records.
     */
    @Nullable
    @Include
    @Column(name = "y")
    private Double y;

    /**
     * Indicates whether the point is active or not.
     * This field is optional and can hold a Boolean value.
     * It is serialized and deserialized using the JSON key "active".
     */
    @Nullable
    @Include
    @Column(name = "active")
    private Boolean active;

    /**
     * Represents the previous state or predecessor of the current entity.
     * This field is optional and can be null. It can be serialized to and
     * deserialized from JSON using the key "prev".
     */
    @Nullable
    @Include
    @Column(name = "prev")
    private String prev;

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
