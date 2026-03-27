/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.mapper;

import edu.kit.iai.webis.proofmodels.dao.AttachmentDao;
import edu.kit.iai.webis.proofmodels.dto.Attachment;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public interface AttachmentMapper {

    Attachment convert(AttachmentDao attachmentDao);

    AttachmentDao convert(Attachment attachment);

    List<Attachment> convert(List<AttachmentDao> attachments);
}