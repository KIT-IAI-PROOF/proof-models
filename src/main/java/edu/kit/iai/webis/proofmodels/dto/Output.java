/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dao.OutputDao;
import edu.kit.iai.webis.proofmodels.enums.ECommunicationType;
import edu.kit.iai.webis.proofmodels.enums.EIOType;
import edu.kit.iai.webis.proofmodels.enums.ESimulationPhase;
import edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.Instant;

import static lombok.ToString.Include;

/**
 * DTO for {@link OutputDao}
 */
@Slf4j
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Output implements Serializable {

    @Include
    @JsonInclude
    @JsonProperty("id")
    @Nullable
    @JsonView(Listing.class)
    private String id;

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
    @JsonInclude
    @Nullable
    @JsonProperty("type")
    @JsonView(Listing.class)
    private EIOType type;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("unit")
    @JsonView(Listing.class)
    private String unit;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("phase")
    @JsonView(Detail.class)
    private ESimulationPhase phase;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("modelVarName")
    @JsonView(Detail.class)
    private String modelVarName;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("communicationType")
    @JsonView(Detail.class)
    private ECommunicationType communicationType;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("createdBy")
    @JsonView(Listing.class)
    private String createdBy;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("lastModifiedBy")
    @JsonView(Listing.class)
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
