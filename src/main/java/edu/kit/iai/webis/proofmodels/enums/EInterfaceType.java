/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.enums;

import edu.kit.iai.webis.proofmodels.dao.ProgramDao;

/**
 * Possible data handling types for the interface.
 */
public enum EInterfaceType {
    /**
     * Writes to a file, which can be handled by a {@link ProgramDao}.
     */
    FILE,
    /**
     * Writes to the console of the {@link ProgramDao}.
     */
    STDIO,
    /**
     *
     */
    SOCKET
}
