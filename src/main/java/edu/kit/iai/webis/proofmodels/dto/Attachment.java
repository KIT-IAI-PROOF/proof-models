
/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.Instant;

import static edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import static edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import static lombok.ToString.Include;

/**
 * DTO for {@link Attachment}
 */
@Slf4j
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Attachment implements Serializable {

    @Include
    @Nullable
    @JsonInclude
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
    @Nullable
    @JsonInclude
    @JsonProperty("description")
    @JsonView(Listing.class)
    private String description;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("path")
    @JsonView(Listing.class)
    private String path;

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
