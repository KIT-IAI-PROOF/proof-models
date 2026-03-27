/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dao.WorkflowDao;
import edu.kit.iai.webis.proofmodels.enums.ECommunicationParadigm;
import edu.kit.iai.webis.proofmodels.enums.ESimulationStrategy;
import edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static lombok.ToString.Include;

/**
 * DTO for {@link WorkflowDao}
 */
@Slf4j
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Workflow implements Serializable {

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("id")
    @JsonView(Listing.class)
    private String id;

    @Include
    @Nullable
    @JsonInclude
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
    @JsonProperty("communicationParadigm")
    @JsonView(Detail.class)
    private ECommunicationParadigm communicationParadigm;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("simulationStrategy")
    @JsonView(Detail.class)
    private ESimulationStrategy simulationStrategy;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("stepBasedConfig")
    @JsonView(Detail.class)
    private StepBasedConfiguration stepBasedConfig;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("blocks")
    @JsonView(Detail.class)
    private List<Block> blocks;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("connections")
    @JsonView(Detail.class)
    private List<Connection> connections;

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

}
