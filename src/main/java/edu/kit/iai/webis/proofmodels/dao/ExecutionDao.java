/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;

import edu.kit.iai.webis.proofmodels.enums.ESimulationStatus;
import edu.kit.iai.webis.proofmodels.enums.EInterfaceType;
import edu.kit.iai.webis.proofmodels.enums.EProcessEnvironment;
import edu.kit.iai.webis.proofmodels.enums.ESimulationStrategy;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_ID_NAME;
import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_LABEL_NAME;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.EAGER;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * The ExecutionDao class represents a database entity for handling execution workflows.
 * It extends the AAuditable abstract class to provide auditability features such as
 * tracking creation and modification metadata. The class is designed to support
 * serialization and deserialization via JSON and includes annotations for JPA
 * persistence, ensuring seamless integration with relational databases.
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "execution")
public class ExecutionDao extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 1375823984734L;

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
     * Represents a label associated with an entity.
     * The label is an optional string value that can provide additional descriptive
     * information. This field may be serialized to and deserialized from JSON
     * using the key "label". The value is stored in the database under the column
     * named "label".
     */
    @Nullable
    @Include
    @Column(name = COLUMN_LABEL_NAME)
    private String label;

    /**
     * Represents the status of the execution entity.
     * This field is optional and can be null.
     * It is serialized to and deserialized from JSON using the key "status".
     * The value corresponds to an enumeration of type ESimulationStatus.
     * Stored in the database under the column "status".
     */
    @Nullable
    @Include
    @Column(name = "status")
    private ESimulationStatus status;

    /**
     * Represents the current simulation step of the execution entity.
     * This field is optional and can be null.
     * It is serialized to and deserialized from JSON using the key "CP".
     * Stored in the database under the column "CP".
     */
    @Nullable
    @Include
    @Column(name = "currentCP")
    private Integer currentCommunicationPoint;

    /**
     * Represents a description associated with an entity.
     * The description is an optional string value that can provide additional descriptive
     * information. This field may be serialized to and deserialized from JSON
     * using the key "description". The value is stored in the database under the column
     * named "description".
     */
    @Nullable
    @Include
    @Column(name = "description")
    private String description;

    /**
     * Represents the start time of an execution process.
     * <p>
     * This field captures the timestamp indicating when the execution began.
     * It is stored in the database as a nullable column and serialized/deserialized
     * using the JSON key "startedAt".
     * <p>
     * The value is optional and defaults to an empty string if no start time is provided.
     */
    @Nullable
    @Include
    @Column(name = "started_at")
    private String startedAt;

    /**
     * Represents the timestamp when the execution process was stopped.
     * This field is optional and can be null.
     * It is stored in the `stopped_at` column in the database.
     * Supports JSON serialization and deserialization using the key "stoppedAt".
     * Default value is an empty string.
     */
    @Nullable
    @Include
    @Column(name = "stopped_at")
    private String stoppedAt;

    /**
     * Represents the options for executions, encapsulated by an
     * instance of {@link ExecutionsOptionsDao}. This field may
     * optionally be null, indicating the absence of specific options.
     */
    @Include
    @Nullable
    @Column(name = "process_env")
    private EProcessEnvironment processEnvironment;

    /**
     * Represents the options for executions, encapsulated by an
     * instance of {@link ExecutionsOptionsDao}. This field may
     * optionally be null, indicating the absence of specific options.
     */
    @Include
    @Embedded
    @Nullable
    private ExecutionsOptionsDao options;

    /**
     * Represents the workflow associated with this entity.
     * WorkflowDao is an optional relationship that may or may not be present.
     * <p>
     * The field is annotated to be ignored during certain serialization processes,
     * and the association is managed lazily to optimize performance.
     * Cascade operations are applied to persist changes to the related WorkflowDao entity.
     * Maps to the "workflow_id" column in the database, referencing the "id" column
     * of the WorkflowDao entity.
     */
    @Include
    @Nullable
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = EAGER, targetEntity = WorkflowDao.class, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "workflow_id", referencedColumnName = "id")
    private WorkflowDao workflow;

    /**
     * Represents the specific type of interface used by the block as defined
     * by the {@link EInterfaceType} enumeration. It determines the communication
     * or data handling method, such as FILE, STDIO, or SOCKET.
     */
    @Nullable
    @Include
    @Column(name = "interface_type")
    private EInterfaceType interfaceType;

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

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "APPLIED_INPUTS")
    @MapKeyColumn(name = "id", length = 50)
    @Column(name = "value", length = 1000)
    @BatchSize(size = 20)
    private Map<String, String> appliedInputs;

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
