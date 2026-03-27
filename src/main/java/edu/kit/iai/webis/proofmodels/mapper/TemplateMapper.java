/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.mapper;

import edu.kit.iai.webis.proofmodels.dao.TemplateDao;
import edu.kit.iai.webis.proofmodels.dto.Template;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        uses = {ProgramMapper.class},
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public interface TemplateMapper {

    Template convert(TemplateDao templateDao);

    TemplateDao convert(Template template);

    List<Template> convert(List<TemplateDao> templates);
}