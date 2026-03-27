/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serial;
import java.io.Serializable;

import static lombok.ToString.Include;

/**
 * Represents the data access object (DAO) for execution options in workflows.
 * This class acts as a container for configuration flags that influence
 * workflow behavior during execution, primarily used for development and testing purposes.
 */
@Slf4j
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionsOptionsDao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1834473667564L;

    /**
     * For development, it is possible to start a workflow without blocks doing anything.
     * For testing purposes.
     */
    @Nullable
    @Include
    private Boolean override;

    /**
     * For development, right now always false. Enables the possibility to manually go over the steps of a block.
     */
    @Nullable
    @Include
    private Boolean manual;

}
