/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;


import edu.kit.iai.webis.proofmodels.enums.ECommunicationParadigm;
import edu.kit.iai.webis.proofmodels.enums.ESimulationStrategy;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_ID_NAME;
import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_LABEL_NAME;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * Represents a WorkflowDao entity with attributes and relationships
 * defining its structure and behavior. A WorkflowDao consists of various
 * properties and associated entities such as blocks, connections.
 * It is a core component modeled within the system and adheres to specific
 * configurations for step-based processing and asynchronous actions.
 * <p>
 * The WorkflowDao class makes use of Lombok annotations for boilerplate reduction
 * and is strictly a persistence model (JPA entity). Any API serialization is handled
 * outside of the DAO layer (e.g., via DTOs or mappers).
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workflow")
public class WorkflowDao extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 4053583454875L;

    /**
     * Represents the unique identifier for an entity or object.
     * This field is mapped to a database column name defined by {@code COLUMN_ID_NAME}.
     * It is marked as nullable to indicate that the value can be absent in certain contexts.
     * The {@code @Id} annotation identifies this field as a primary key in the database.
     * API exposure of this value (if any) is handled outside the DAO layer.
     */
    @Id
    @Nullable
    @Include
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private String id;

    /**
     * Represents a label as a string. This field is optional and can be null.
     * It is mapped to the database column "label".
     */
    @Nullable
    @Include
    @Column(name = COLUMN_LABEL_NAME)
    private String label;

    /**
     * Represents the description of an entity. This field is optional and can be null.
     * It is mapped to the "description" column in the database for persistence.
     */
    @Nullable
    @Include
    @Column(name = "description")
    private String description;

    /**
     * Represents the communication paradigm associated with a given entity.
     * Communication paradigm defines the model or methodology by which
     * communication between entities is structured or managed.
     * <p>
     * This variable can be null and is mapped to a database column named
     * "communication_paradigm".
     */
    @Nullable
    @Include
    @Column(name = "communication_paradigm")
    private ECommunicationParadigm communicationParadigm;

    /**
     * Represents an asynchronous action associated with the entity.
     * The value is optional and may be null. It is included in the
     * JSON serialization and deserialization process. This variable
     * is mapped to the "asyncAction" column in the database.
     */
    @Nullable
    @Include
    @Column(name = "simulation_strategy")
    private ESimulationStrategy simulationStrategy;

    /**
     * Represents the step-based configuration for a specific process or component.
     * This variable holds the configuration details that are used to define
     * step-by-step settings and behaviors.
     */
    @Include
    @Nullable
    @OneToOne(fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "step_based_config_id", referencedColumnName = "id")
    private StepBasedConfigurationDao stepBasedConfig;

    /**
     * Represents a list of blocks associated with the workflow.
     * This list is managed with operations such as fetch, cascade, and orphan removal.
     * The blocks are mapped by the "workflow" field in the BlockDao class.
     * The blocks are included in JSON serialization/deserialization when not null.
     */
    @Include
    @Nullable
    @OneToMany(targetEntity = BlockDao.class, fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "workflow_id", referencedColumnName = "id")
    private List<BlockDao> blocks;

    /**
     * Represents the list of connections associated with a workflow.
     * Each connection is an instance of the {@code ConnectionDao} class and represents a connection or relationship in the workflow.
     * This list is managed as a one-to-many relationship, mapped by the "workflow" field in the {@code ConnectionDao} entity.
     * Connections are fetched eagerly and support cascade operations, including persist, merge, remove, refresh, and detach.
     * Orphan removal is enabled, ensuring that any connection removed from the list is also deleted from the database.
     */
    @Include
    @Nullable
    @OneToMany(targetEntity = ConnectionDao.class, fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "workflow_id", referencedColumnName = "id")
    private List<ConnectionDao> connections;

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
