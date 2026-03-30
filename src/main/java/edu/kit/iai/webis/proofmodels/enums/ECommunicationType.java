/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.enums;

/**
 * Possible WorkflowDao execution types.
 * Currently, {@link #STEPBASED} and {@link #EVENT} are supported.
 * Future versions may include hybrid simulation types.
 */
public enum ECommunicationType {
    /**
     * See {@link ECommunicationParadigm#STEPBASED}
     */
    STEPBASED,
    /**
     * See {@link ECommunicationParadigm#EVENT}
     */
    EVENT,
    /**
     * Used for initial static value setting.
     * See {@link ECommunicationParadigm#EVENT}
     */
    EVENT_STATIC,
    /**
     * Used for initial static value setting.
     * See {@link ECommunicationParadigm#STEPBASED}
     */
    STEPBASED_STATIC
}
