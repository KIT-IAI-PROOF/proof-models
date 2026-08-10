/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dao.ExecutionDao;
import edu.kit.iai.webis.proofmodels.enums.ESimulationStatus;
import edu.kit.iai.webis.proofmodels.enums.EInterfaceType;
import edu.kit.iai.webis.proofmodels.enums.EProcessEnvironment;
import edu.kit.iai.webis.proofmodels.enums.ESimulationStrategy;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

import static edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import static edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import static lombok.ToString.Include;

/**
 * DTO for {@link ExecutionDao}
 */
@Slf4j
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Execution implements Serializable {

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("id")
    @JsonView(Listing.class)
    private String id;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("status")
    @JsonView(Listing.class)
    private ESimulationStatus status;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("label")
    @JsonView(Listing.class)
    private String label;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("description")
    @JsonView(Listing.class)
    private String description;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("startedAt")
    @JsonView(Listing.class)
    private String startedAt;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("stoppedAt")
    @JsonView(Listing.class)
    private String stoppedAt;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("options")
    @JsonView(Detail.class)
    private ExecutionsOptions options;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("execParameters")
    @JsonView(Listing.class)
    private Map<String, String> execParameters;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("execStartValues")
    @JsonView(Detail.class)
    private Map<String, String> execStartValues;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("execDefaultValues")
    @JsonView(Detail.class)
    private Map<String, String> execDefaultValues;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("workflow")
    @JsonView(Listing.class)
    private Workflow workflow;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("createdBy")
    @JsonView(Detail.class)
    private String createdBy;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("lastModifiedBy")
    @JsonView(Detail.class)
    private String lastModifiedBy;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("interfaceType")
    @JsonView(Detail.class)
    private EInterfaceType interfaceType;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("processEnvironment")
    @JsonView(Detail.class)
    private EProcessEnvironment processEnvironment;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("simulationStrategy")
    @JsonView(Detail.class)
    private ESimulationStrategy simulationStrategy;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("creationDate")
    @JsonView(Listing.class)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant creationDate;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("lastModifiedDate")
    @JsonView(Listing.class)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant lastModifiedDate;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("currentCP")
    @JsonView(Detail.class)
    private Integer currentCommunicationPoint;

}