/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;

import edu.kit.iai.webis.proofmodels.enums.ECommunicationType;
import edu.kit.iai.webis.proofmodels.enums.EIOType;
import edu.kit.iai.webis.proofmodels.enums.ESimulationPhase;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;

import static edu.kit.iai.webis.proofmodels.utils.Constants.COLUMN_LABEL_NAME;
import static lombok.ToString.Include;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AIOElement extends AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 68943558237483L;

    /**
     * Represents a label associated with an object, primarily used for descriptive or display purposes.
     * It can be null, which indicates the absence of any specific label.
     */
    @Nullable
    @Include
    @Column(name = COLUMN_LABEL_NAME)
    private String label;

    /**
     * Represents the description associated with a particular entity or object.
     * This field is optional and can be null if no description is provided.
     * It is mapped to the "description" column in the database schema.
     */
    @Nullable
    @Include
    @Column(name = "description")
    private String description;

    @Nullable
    @Include
    @Column(name = "type")
    private EIOType type;

    @Nullable
    @Include
    @Column(name = "unit")
    private String unit;

    @Nullable
    @Include
    @Column(name = "phase")
    private ESimulationPhase phase;

    @Nullable
    @Include
    @Column(name = "communication_type")
    private ECommunicationType communicationType;

    @Nullable
    @Include
    @Column(name = "model_var_name")
    private String modelVarName;
}
