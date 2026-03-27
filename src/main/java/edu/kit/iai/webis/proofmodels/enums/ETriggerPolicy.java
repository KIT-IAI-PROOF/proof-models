/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.enums;

/**
 * Trigger Policy for event based activation.
 */
public enum ETriggerPolicy {
    /**
     * Only do event, if all dependencies changed.
     */
    ON_ALL_CHANGE,
    /**
     * Do event if any dependecy changed.
     */
    ON_ANY_CHANGE
}
