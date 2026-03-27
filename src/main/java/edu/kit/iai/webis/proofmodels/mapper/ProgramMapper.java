/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.mapper;

import edu.kit.iai.webis.proofmodels.dao.ProgramDao;
import edu.kit.iai.webis.proofmodels.dto.Program;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        uses = {AttachmentMapper.class},
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL
)
public interface ProgramMapper {

    Program convert(ProgramDao programDao);

    ProgramDao convert(Program program);

    List<Program> convert(List<ProgramDao> programs);

}