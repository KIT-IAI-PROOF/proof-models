/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.BatchSize;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_ID_NAME;
import static jakarta.persistence.FetchType.EAGER;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * The StepSizeDefinitionDao class represents the definition of step sizes for a given configuration.
 * It includes metadata and specific attributes relating to step sizes and their associated properties.
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "step_size_definition")
public class StepSizeDefinitionDao extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 76553583454875L;

    /**
     * Represents the unique identifier for an entity.
     * This field is used as the primary key and is a required property.
     * The value may be null in certain scenarios but is typically mandatory.
     */
    @Id
    @Nullable
    @Include
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private String id;

    @Nullable
    @Include
    @Column(name = "step")
    private String step;

    /**
     * Represents the starting point of a range or process.
     * This field is mandatory and cannot be null.
     * The default value is initialized to 0.
     */
    @Nullable
    @Include
    @Column(name = "start_point")
    private Integer startPoint;

    /**
     * Represents the optional endpoint value for a step size definition.
     * This field can be null, indicating that no specific endpoint is set.
     * Additionally, it is mapped to the database column "end_point" and is
     * marked as nullable in the database schema.
     */
    @Nullable
    @Include
    @Column(name = "end_point")
    private Integer endPoint;

    /**
     * Specifies the default size used for a particular step or configuration.
     * This field is optional and allows for null values.
     * It is mapped to the database column "default_size" and serialized/deserialized
     */
    @Nullable
    @Include
    @Column(name = "default_size")
    private Integer defaultSize;

    /**
     * Represents a collection of step sizes, where each step size is associated with a unique identifier.
     * This is a map structure with the key as a unique string identifier and the value as an integer
     * representing the step size value.
     */
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "STEPSIZES")
    @Nullable
    @MapKeyColumn(name = "id", length = 50)
    @Column(name = "value", length = 100)
    @BatchSize(size = 20)
    private Map<String, Integer> stepSizes;

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
