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
 * Represents a two-dimensional position with optional x and y coordinates.
 * This class is designed to be lightweight and serializable for various use cases,
 * including data transfer and storage.
 */
@Slf4j
@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PositionDao implements Serializable {

    @Serial
    private static final long serialVersionUID = 5834573487564L;

    /**
     * Represents the x-coordinate of a two-dimensional position.
     * This field is optional and may hold a null value if the x-coordinate is not specified.
     * It is serialized and deserialized as part of the JSON representation of the class.
     */
    @Nullable
    @Include
    private Integer x;

    /**
     * Represents the Y-coordinate in a two-dimensional position.
     * This field is optional and can have a null value, indicating the absence
     * of a Y-coordinate. It is serialized and deserialized in JSON as "y".
     * The value is included only if non-null during serialization.
     */
    @Nullable
    @Include
    private Integer y;

}
