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
import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_LABEL_NAME;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * The AttachmentDao class represents the data access object for attachment entities in the system.
 * It is a JPA entity that handles persistence and provides fields mapping to the corresponding database table.
 * Each instance of this class corresponds to a row in the ATTACHMENT database table.
 * The entity includes auditing capabilities by extending the AAuditable base class.
 * It is also serializable for potential serialization and transfer needs.
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attachment")
public class AttachmentDao extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 2247844366343L;

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
     * Represents the file path or location associated with an attachment entity.
     * This field is annotated to support serialization as a binary representation.
     * It is optional and can be null if no path is specified.
     * The data is mapped to the path column in the database schema.
     */
    @Nullable
    @Include
    @Column(name = "path")
    private String path;

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
