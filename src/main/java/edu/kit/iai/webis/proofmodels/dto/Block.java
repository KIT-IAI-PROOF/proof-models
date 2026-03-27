/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dao.BlockDao;
import edu.kit.iai.webis.proofmodels.enums.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import static edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import static lombok.ToString.Include;

/**
 * DTO for {@link BlockDao}
 */
@Slf4j
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Block implements Serializable {

    @Include
    @JsonInclude
    @JsonProperty("id")
    @Nullable
    @JsonView(Listing.class)
    private String id;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("templateId")
    @JsonView(Detail.class)
    private String templateId;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("templateName")
    @JsonView(Detail.class)
    private String templateName;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("index")
    @JsonView(Listing.class)
    private Integer index;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("label")
    @JsonView(Listing.class)
    private String label;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("description")
    @JsonView(Listing.class)
    private String description;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("color")
    @JsonView(Listing.class)
    private String color;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("textColor")
    @JsonView(Listing.class)
    private String textColor;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("type")
    @JsonView(Detail.class)
    private String type;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("blockType")
    @JsonView(Detail.class)
    private EBlockType blockType;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("containerImage")
    @JsonView(Detail.class)
    private String containerImage;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("status")
    @JsonView(Detail.class)
    private ESimulationStatus status;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("communicationParadigm")
    @JsonView(Detail.class)
    private ECommunicationParadigm communicationParadigm;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("syncStrategy")
    @JsonView(Detail.class)
    private ESyncStrategy syncStrategy;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("position")
    @JsonView(Detail.class)
    private Position position;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("program")
    @JsonView(Detail.class)
    private Program program;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("outputs")
    @JsonView(Detail.class)
    private List<Output> outputs;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("inputs")
    @JsonView(Detail.class)
    private List<Input> inputs;

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

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("shutdownRelevant")
    @JsonView(Detail.class)
    private Boolean shutdownRelevant;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("interfaceType")
    @JsonView(Detail.class)
    private EInterfaceType interfaceType;

}
