/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;

import edu.kit.iai.webis.proofmodels.enums.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_ID_NAME;
import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * Represents a TemplateDao entity which includes properties such as label, description, color, type, and identifier.
 * It also maintains relationships with Outputs and Inputs.
 * The class leverages various annotations for database mapping, serialization, and auditing functionalities.
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "template")
public class TemplateDao extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 3442374834495L;

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

    @Nullable
    @Include
    @Column(name = "name", unique = true)
    private String name;

    /**
     * Represents the description associated with a particular entity or object.
     * This field is optional and can be null if no description is provided.
     * It is mapped to the "description" column in the database schema and serialized
     * with JSON using the key "description".
     */
    @Nullable
    @Include
    @Column(name = "description")
    private String description;

    /**
     * Represents the color property as a String. This variable is included in serialization
     * and can be null. It is mapped to the "color" column in the database and is exposed as
     * "color" in JSON representations.
     */
    @Nullable
    @Include
    @Column(name = "color")
    private String color;

    @Nullable
    @Include
    @Column(name = "text_color")
    private String textColor;

    /**
     * Represents the type of a block
     * <p>
     * This field is optional and can be null. It is serialized into JSON
     * with the key "type" when included. The value is stored in the database
     * column "type".
     */
    @Nullable
    @Include
    @Column(name = "type")
    private String type;

    @Nullable
    @Include
    @Column(name = "block_type")
    private EBlockType blockType;

    /**
     * Represents the container image associated with a specific entity.
     * This variable holds the reference or identifier for the container image
     * that is used in the context of the application.
     */
    @Nullable
    @Include
    @Column(name = "container_image")
    private String containerImage;

    /**
     * Indicates whether the block is relevant for shutdown procedures. If true, the block
     * is considered important during shutdown operations. This means that the block will cause the
     * workflow to be shut down after the block has finished normally
     */
    @Nullable
    @Include
    @Column(name = "shutdown_relevant")
    private Boolean shutdownRelevant;

    /**
     * Represents the status of a block. This variable can hold a value of type ESimulationStatus
     * or be null if no status is assigned. The status is stored as a column in a database
     * table and its name is defined as "status". The column is nullable, meaning null values
     * are allowed for this field. It is marked with annotations for additional metadata or
     * processing purposes.
     */
    @Nullable
    @Include
    @Column(name = "status")
    private ESimulationStatus status;

    /**
     * Represents the communication paradigm used in a particular context.
     * This variable is mapped to the "communication_paradigm" database column
     * and its value can be null. It signifies the method or approach taken
     * for communication, defined by the {@link ECommunicationParadigm} enumeration.
     */
    @Nullable
    @Include
    @Column(name = "communication_paradigm")
    private ECommunicationParadigm communicationParadigm;

    /**
     * Represents the strategy the block should use
     * by the {@link ESyncStrategy} enumeration.
     */
    @Nullable
    @Include
    @Column(name = "sync_strategy")
    private ESyncStrategy syncStrategy;

    /**
     * Represents the specific type of interface used by the block as defined
     * by the {@link EInterfaceType} enumeration. It determines the communication
     * or data handling method, such as FILE, STDIO, or SOCKET.
     */
    @Nullable
    @Include
    @Column(name = "interface_type")
    private EInterfaceType interfaceType;

    @Include
    @Nullable
    @ManyToOne(fetch = EAGER, targetEntity = ProgramDao.class, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    private ProgramDao program;

    /**
     * Represents a list of OutputDao entities associated with a block.
     * This field is mapped as a one-to-many relationship where each OutputDao
     * is linked to a specific block. The relationship is fetched eagerly, and
     * cascading operations such as persist, merge, remove, etc., are applied to
     * related entities. Orphan removal is enabled to delete any OutputDao
     * entities that are no longer associated with the block.
     * <p>
     * When serialized to JSON, this property is included only if it is not null.
     * Modifications to this field are restricted as its setter method is
     * intentionally omitted to ensure controlled management through other means.
     */
    @Include
    @Nullable
    @OneToMany(targetEntity = OutputDao.class, fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    private List<OutputDao> outputs;

    /**
     * Represents a list of target handles associated with a specific block.
     * This collection is managed with `@OneToMany` relationship indicating
     * that one block can have multiple target handles. The relationship is
     * configured to eagerly fetch the associated target handles and implement
     * cascading operations including all persistence context changes. Orphan
     * removal is also enabled to automatically remove target handles that
     * are no longer associated with the block.
     * <p>
     * This list stores instances of {@link InputDao} and provides an
     * association from the perspective of the owning block entity.
     */
    @Include
    @Nullable
    @OneToMany(targetEntity = InputDao.class, fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "template_id", referencedColumnName = "id")
    private List<InputDao> inputs;

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
