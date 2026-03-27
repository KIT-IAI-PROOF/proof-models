/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dao.ExecutionsOptionsDao;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serializable;

import static edu.kit.iai.webis.proofmodels.utils.Views.Detail;
import static lombok.ToString.Include;

/**
 * DTO for {@link ExecutionsOptionsDao}
 */
@Slf4j
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExecutionsOptions implements Serializable {

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("override")
    @JsonView(Detail.class)
    private Boolean override;

    @Include
    @JsonInclude
    @Nullable
    @JsonProperty("manual")
    @JsonView(Detail.class)
    private Boolean manual;

}
