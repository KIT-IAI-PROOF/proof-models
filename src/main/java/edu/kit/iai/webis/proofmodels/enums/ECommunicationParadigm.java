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
public enum ECommunicationParadigm {
    /**
     * Uses time-based steps.
     * Incorporates master-slave architecture.
     */
    STEPBASED,
    /**
     * Uses events to control workflow execution.
     */
    EVENT
}
