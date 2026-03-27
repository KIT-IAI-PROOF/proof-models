/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dao.ConnectionDao;
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
 * DTO for {@link ConnectionDao}
 */
@Slf4j
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Connection implements Serializable {

    @Include
    @JsonInclude
    @JsonProperty("id")
    @Nullable
    @JsonView(Listing.class)
    private String id;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("type")
    @JsonView(Detail.class)
    private String type;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("connectionType")
    @JsonView(Detail.class)
    private String connectionType;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("source")
    @JsonView(Detail.class)
    private String source;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("output")
    @JsonView(Detail.class)
    private String output;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("target")
    @JsonView(Detail.class)
    private String target;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("input")
    @JsonView(Detail.class)
    private String input;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("animated")
    @JsonView(Detail.class)
    private Boolean animated;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("algorithm")
    @JsonView(Detail.class)
    private String algorithm;

    @Include
    @Nullable
    @JsonInclude
    @JsonProperty("points")
    @JsonView(Detail.class)
    private List<Point> points;

    @Include
    @JsonInclude
    @Nullable
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
