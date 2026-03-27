/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.enums;

/**
 * Possible runtimes to execute.
 */
public enum ERuntime {

    /**
     * Use development environment with common used python libraries.
     */
    PYTHON("python3"),
    /**
     * Use development environment with common used java libraries.
     */
    JAVA("java -jar"),

    /**
     * Use development environment with common used matlab runtime.
     */
    MATLAB("");

    /**
     * Command to use in executor.
     */
    public final String command;

    ERuntime(String command) {
        this.command = command;
    }
}
