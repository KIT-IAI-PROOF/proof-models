/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dao.StepSizeDefinitionDao;
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
 * DTO for {@link StepSizeDefinitionDao}
 */
@Slf4j
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepSizeDefinition implements Serializable {

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("id")
    @JsonView(Listing.class)
    private String id;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("step")
    @JsonView(Detail.class)
    private String step;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("startPoint")
    @JsonView(Detail.class)
    private Integer startPoint;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("endPoint")
    @JsonView(Detail.class)
    private Integer endPoint;

    @Include
    @JsonInclude
    @JsonProperty("defaultSize")
    @JsonView(Detail.class)
    private Integer defaultSize;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("stepSizes")
    @JsonView(Detail.class)
    private Map<String, Integer> stepSizes;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("createdBy")
    @JsonView(Detail.class)
    private String createdBy;

    @Include
    @Nullable
    @JsonInclude
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
