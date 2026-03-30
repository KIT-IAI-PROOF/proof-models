/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dao.PointDao;
import edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.Instant;

import static edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import static lombok.ToString.Include;

/**
 * DTO for {@link PointDao}
 */
@Slf4j
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Point implements Serializable {

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("id")
    @JsonView(Listing.class)
    private String id;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("x")
    @JsonView(Detail.class)
    private Double x;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("y")
    @JsonView(Detail.class)
    private Double y;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("active")
    @JsonView(Detail.class)
    private Boolean active;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("prev")
    @JsonView(Detail.class)
    private String prev;

    @Include
    @Nullable
    @JsonInclude
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
