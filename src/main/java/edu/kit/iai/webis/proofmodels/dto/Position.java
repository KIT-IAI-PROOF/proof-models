/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dao.PositionDao;
import lombok.*;
import org.springframework.lang.Nullable;

import java.io.Serializable;

import static edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import static lombok.ToString.Include;

/**
 * DTO for {@link PositionDao}
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Position implements Serializable {

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("x")
    @JsonView(Detail.class)
    private Integer x;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("y")
    @JsonView(Detail.class)
    private Integer y;

}