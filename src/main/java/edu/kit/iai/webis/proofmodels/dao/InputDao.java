/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;

import edu.kit.iai.webis.proofmodels.enums.ECommunicationType;
import edu.kit.iai.webis.proofmodels.enums.EIOType;
import edu.kit.iai.webis.proofmodels.enums.ESimulationPhase;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;

import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_ID_NAME;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Include;

/**
 * Represents the InputDao entity used in the application. This class is mapped to the database table
 * "INPUT" and serves as an auditable entity. It provides fields for key properties such as unique
 * identifiers, labels, and relationships to other entities like BlockDao and TemplateDao.
 */
@Slf4j
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "input")
public class InputDao extends AIOElement implements Serializable {

    @Serial
    private static final long serialVersionUID = 23475856473545L;

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
    @Column(name = "required")
    private Boolean required;

    @Nullable
    @Include
    @Column(name = "default_value")
    private String defaultValue;

    @Builder
    @SuppressWarnings("unused")
    public InputDao(@Nullable final String label,
                    @Nullable final String description,
                    @Nullable final EIOType type,
                    @Nullable final String unit,
                    @Nullable final ESimulationPhase phase,
                    @Nullable final ECommunicationType communicationType,
                    @Nullable final String id,
                    @Nullable final Boolean required,
                    @Nullable final String modelVarName,
                    @Nullable final String defaultValue) {
        super(label, description, type, unit, phase, communicationType, modelVarName);
        this.id = id;
        this.required = required;
        this.defaultValue = defaultValue;
    }

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
