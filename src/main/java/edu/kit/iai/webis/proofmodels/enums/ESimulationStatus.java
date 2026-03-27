/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.enums;

import edu.kit.iai.webis.proofmodels.dao.StepSizeDefinitionDao;

/**
 * The status of a WorkflowContainer or a BlockDao.
 * Values may be {@link #CREATED}, {@link #INITIALIZED}, {@link #WAITING}, {@link #READY}, {@link #STOPPED} or
 * {@link #ABORTED} for blocks and workflows,
 * <p>
 * {@link #INITIALIZED}, {@link #EXECUTION_FINISHED}, {@link #FINALIZED}, {@link #ERROR_INIT}, {@link #ERROR_STEP},
 * {@link #ERROR_FINALIZE} or {@link #EXECUTION_STEP_FINISHED} for blocks
 */
public enum ESimulationStatus {

    /**
     * The initial value for a block
     */
    UNKNOWN,

    /**
     * The block is created and ready to work
     */
    CREATED,

    /**
     * A block is initialized, in other words, the initialization phase (phase 1 of 3)
     * has finished.
     */
    INITIALIZED,

    /**
     * NEW MEANING
     * BlockDao got a sync message (phase execute) and is running.
     * The orchestrator is waiting for the notification message with
     * {@link #EXECUTION_STEP_FINISHED} of the running block.
     */
    WAITING,

    /**
     * NEW status has two meanings:
     * 1. The orchestrator can send a sync message to the worker,
     * since the worker is ready to work.
     * 2. The orchestrator has sent a sync message to the worker,
     * but the worker is not yet able to work, because he still lacks information or data.
     * Then the worker sends a notification message with the status READY back to the orchestrator, which means that the
     * worker is ready for receiving the next sync message.
     */
    READY,

    /**
     * A block has finished an execution step in the execution phase (phase 2 of 3).
     */
    EXECUTION_STEP_FINISHED,

    /**
     * A block has finished the execution phase (phase 2 of 3).
     */
    EXECUTION_FINISHED,

    /**
     * A block has passed the finalize phase (phase 3 of 3)
     */
    FINALIZED,

    /**
     * The WorkflowContainer and all its blocks (BlockContainers)
     * are aborted
     */
    ABORTED,

    /**
     * The WorkflowContainer and all its blocks (BlockContainers)
     * are stopped
     */
    STOPPED,

    /**
     * A block (BlockContainer) has been shut down.
     */
    SHUT_DOWN,

    /**
     * A block is suspended when it is waiting for the next working step.
     * This new step depends on the current communication point, and it is corresponding.
     * {@link StepSizeDefinitionDao}
     */
    SUSPENDED,

    /**
     * An error/exception occurred in the init phase (phase 1 of 3).
     */
    ERROR_INIT,

    /**
     * An error/exception occurred in the step phase (phase 2 of 3).
     */
    ERROR_STEP,

    /**
     * An error/exception occurred in the finalize phase (phase 3 of 3).
     */
    ERROR_FINALIZE
}

