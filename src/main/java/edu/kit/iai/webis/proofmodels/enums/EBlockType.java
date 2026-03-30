/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.enums;

import edu.kit.iai.webis.proofmodels.dto.Block;
import edu.kit.iai.webis.proofmodels.dto.Template;

/**
 * The type of a {@link Template} ({@link Block}).
 * Values may be {@link #BASE}, {@link #SPECIFIC}, {@link #HELPER}, or {@link #USERDEFINED}
 */
public enum EBlockType {
    /**
     * a base block like Reader, Writer, ...
     */
    BASE,
    /**
     * a more specific block that is used in selected default workflows
     */
    SPECIFIC,
    /**
     * a helper block like Adder, Multiplexer, ...
     */
    HELPER,
    /**
     * a custom block that is created by the user. It is not part of the default blocks
     */
    USERDEFINED
}
