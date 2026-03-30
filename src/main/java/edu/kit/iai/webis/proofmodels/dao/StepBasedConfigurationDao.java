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
import java.util.Map;

import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_ID_NAME;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * The StepBasedConfigurationDao class represents a configuration entity with step-based attributes.
 * It contains information such as start time, end time, duration, step sizes, and associated step size definitions.
 * This class is designed to be persistent and is mapped to the "STEP_BASED_CONFIG" table in the database.
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "step_based_config")
public class StepBasedConfigurationDao extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 446553583454875L;

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
    @Column(name = "start_time")
    private Long startTime;

    /**
     * Represents the end time of a process or configuration step, expressed as a long value.
     * The default value is set to 1000L.
     */
    @Nullable
    @Include
    @Column(name = "end_time")
    private Long endTime;

    /**
     * Represents the starting point of a configuration or range.
     * This value is intended to be an integer and defaults to 0.
     * The field is optional and may remain null depending on the use case.
     */
    @Nullable
    @Include
    @Column(name = "start_point")
    private Integer startPoint;

    /**
     * Represents the endpoint value of the configuration.
     * It is mapped to the "end_point" column in the database with optional nullability.
     * The default value is set to 1000.
     */
    @Nullable
    @Include
    @Column(name = "end_point")
    private Integer endPoint;

    /**
     * Represents the duration for a specific process or operation.
     * The value is expressed as a Long in milliseconds and can be null.
     */
    @Nullable
    @Include
    @Column(name = "duration")
    private Long duration;

    /**
     * Represents the default step size used in configurations or calculations.
     * This value determines the increment between successive steps.
     * The default value is set to 1, and it is a non-null property.
     */
    @Nullable
    @Include
    @Column(name = "default_step_size")
    private Integer defaultStepSize;

    /**
     * Represents a map of step size definitions associated with the current step-based configuration.
     * Each entry in the map is identified by a unique key, usually corresponding to the "id" field
     * of the associated StepSizeDefinitionDao entity.
     */
    @Include
    @Nullable
    @OneToMany(targetEntity = StepSizeDefinitionDao.class, fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "step_based_config_id", referencedColumnName = "id")
    private Map<String, StepSizeDefinitionDao> stepSizeDefinitions;

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
