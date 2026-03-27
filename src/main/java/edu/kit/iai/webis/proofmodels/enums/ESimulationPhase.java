/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.enums;

/**
 * Phases of a workflow and its blocks during a simulation. Note: A workflow is in a particular phase, when not all blocks have finished this phase.
 * When the last block has finished the phase, the next phase will be entered until finalize is passed.
 */
public enum ESimulationPhase {
    /**
     * Used for initial setup.
     */
    CREATE,
    /**
     * Used for initial setup.
     */
    INIT,
    /**
     * Used as main action, the processing of the model for several steps.
     */
    EXECUTE,
    /**
     * Used to clean up the process.
     */
    FINALIZE,
    /**
     * Used to shut down a block.
     */
    SHUTDOWN

}
