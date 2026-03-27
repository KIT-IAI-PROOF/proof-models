/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;

import edu.kit.iai.webis.proofmodels.enums.ERuntime;
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
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.EAGER;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * Represents a Data Access Object (DAO) for the Program entity.
 * This class is responsible for persisting and retrieving information
 * related to programs in the database. It extends AAuditable to
 * include auditing features and implements Serializable to support
 * object serialization.
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "program")
public class ProgramDao extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 1347893478233L;

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
     * Represents a label associated with an object, primarily used for descriptive or display purposes.
     * It can be null, which indicates the absence of any specific label.
     * This field is serialized into JSON when included in API responses or requests.
     */
    @Nullable
    @Include
    @Column(name = COLUMN_LABEL_NAME)
    private String label;

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
     * Represents a tag associated with the program entity, used to categorize or provide additional metadata.
     * This field is mapped to the "tag" column in the database schema. It is optional and can be null if
     * no tag is specified. The value of this field is included in serialized JSON output when applicable.
     */
    @Nullable
    @Include
    @Column(name = "tag")
    private String tag;

    /**
     * Represents the runtime environment or configuration of a program.
     * This field can accept values defined by the {@link ERuntime} enumeration.
     * It is optional and can be null, indicating that no specific runtime is configured.
     * Mapped to the database column "runtime".
     */
    @Nullable
    @Include
    @Column(name = "runtime")
    private ERuntime runtime;

    /**
     * Represents the entry point of a program or process.
     * This field optionally specifies a starting point or main method
     * within the program. It can be null if no entry point is defined.
     * Mapped to the "entry_point" column in the database schema.
     */
    @Nullable
    @Include
    @Column(name = "entry_point")
    private String entryPoint;

    /**
     * Represents a collection of attachments associated with a program entity.
     * The attachments are managed as a one-to-many relationship where each attachment
     * entity is associated with a specific program. This relationship is bidirectional,
     * allowing for easy navigation between a program and its attachments.
     * <p>
     * The attachments are eagerly fetched, meaning they are loaded immediately along
     * with the program entity. Cascade operations, including persisting, merge, remove, etc.
     * are applied to related attachment entities. Orphan removal ensures that any
     * detached attachment entities are automatically deleted.
     * <p>
     * This field is immutable, and its setter is restricted to prevent external modifications.
     * Attachments are initialized as an empty collection by default.
     * <p>
     * An implementation detail is to update the program reference in each attachment when
     * the attachments are set, ensuring bidirectional consistency.
     */
    @Include
    @Nullable
    @ManyToMany(targetEntity = AttachmentDao.class, fetch = EAGER, cascade = {PERSIST, MERGE})
    @JoinTable(
            name = "program_attached",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"program_id", "attachment_id"})
            }
    )
    private List<AttachmentDao> attachments;

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
