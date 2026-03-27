/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.paging;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.kit.iai.webis.proofmodels.dto.Attachment;
import edu.kit.iai.webis.proofmodels.utils.Views.Listing;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

import static lombok.ToString.Include;

@Data
@Builder
@ToString
@SuppressWarnings("unused")
public class AttachmentPagingModel implements Serializable {

    @Include
    @JsonInclude
    @JsonProperty("rowCount")
    @Nullable
    @JsonView(Listing.class)
    private Long rowCount;

    @Include
    @JsonInclude
    @JsonProperty("results")
    @Nullable
    @JsonView(Listing.class)
    private List<Attachment> results;

}
