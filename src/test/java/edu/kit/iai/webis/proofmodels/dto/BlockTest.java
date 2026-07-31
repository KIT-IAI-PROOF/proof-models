/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dto;

import edu.kit.iai.webis.proofmodels.enums.ESimulationStatus;
import edu.kit.iai.webis.proofmodels.enums.EBlockType;
import edu.kit.iai.webis.proofmodels.enums.ECommunicationParadigm;
import edu.kit.iai.webis.proofmodels.mapper.BlockMapper;
import edu.kit.iai.webis.proofmodels.mapper.BlockMapperImpl;
import edu.kit.iai.webis.proofmodels.mapper.ProgramMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class BlockTest {

    private final ProgramMapper programMapper = mock(ProgramMapper.class);
    private final BlockMapper blockMapper = Mappers.getMapper(BlockMapper.class);

    @BeforeEach
    void setup() throws NoSuchFieldException, IllegalAccessException {
        Field field = BlockMapperImpl.class.getDeclaredField("programMapper");
        field.setAccessible(true);
        field.set(blockMapper, programMapper);
    }

    /**
     * Tests if the convert method correctly maps a Block object to a BlockDao object
     * when all attributes are populated.
     */
    @Test
    public void testConvert_AllFieldsPopulated() {

        // Arrange
        Block block = Block.builder()
                .id("123")
                .templateId("temp123")
                .templateName("templateName")
                .index(1)
                .label("label")
                .description("description")
                .color("blue")
                .type("block")
                .blockType(EBlockType.BASE)
                .containerImage("containerImage")
                .status(ESimulationStatus.ABORTED)
                .position(new Position())
                .program(new Program())
                .communicationParadigm(ECommunicationParadigm.EVENT)
                .outputs(List.of(new Output()))
                .inputs(List.of(new Input()))
                .createdBy("creator")
                .lastModifiedBy("modifier")
                .creationDate(Instant.now())
                .lastModifiedDate(Instant.now())
                .shutdownRelevant(true)
                .build();

        // Act
        final var blockDao = this.blockMapper.convert(block);

        // Assert
        assertEquals(block.getId(), blockDao.getId());
        assertEquals(block.getTemplateId(), blockDao.getTemplateId());
        assertEquals(block.getTemplateName(), blockDao.getTemplateName());
        assertEquals(block.getIndex(), blockDao.getIndex());
        assertEquals(block.getLabel(), blockDao.getLabel());
        assertEquals(block.getDescription(), blockDao.getDescription());
        assertEquals(block.getColor(), blockDao.getColor());
        assertEquals(block.getType(), blockDao.getType());
        assertEquals(block.getContainerImage(), blockDao.getContainerImage());
        assertEquals(block.getStatus(), blockDao.getStatus());
        assertEquals(block.getCommunicationParadigm(), blockDao.getCommunicationParadigm());
        assertEquals(block.getShutdownRelevant(), blockDao.getShutdownRelevant());
        assertEquals(Objects.requireNonNull(block.getOutputs()).size(), Objects.requireNonNull(blockDao.getOutputs()).size());
        assertEquals(Objects.requireNonNull(block.getInputs()).size(), Objects.requireNonNull(blockDao.getInputs()).size());

    }

    /**
     * Tests if the convert method handles nulls properly when optional fields are not populated.
     */
    @Test
    public void testConvert_NullFields() {

        // Arrange
        Block block = Block.builder()
                .id("123")
                .templateId(null)
                .templateName(null)
                .index(null)
                .label(null)
                .description(null)
                .color(null)
                .type(null)
                .containerImage(null)
                .status(null)
                .communicationParadigm(null)
                .position(null)
                .program(null)
                .outputs(null)
                .inputs(null)
                .createdBy(null)
                .lastModifiedBy(null)
                .creationDate(null)
                .lastModifiedDate(null)
                .shutdownRelevant(false)
                .build();

        // Act
        final var blockDao = this.blockMapper.convert(block);

        // Assert
        assertEquals(block.getId(), blockDao.getId());
        assertEquals(block.getTemplateId(), blockDao.getTemplateId());
        assertEquals(block.getTemplateName(), blockDao.getTemplateName());
        assertEquals(block.getIndex(), blockDao.getIndex());
        assertEquals(block.getLabel(), blockDao.getLabel());
        assertEquals(block.getDescription(), blockDao.getDescription());
        assertEquals(block.getColor(), blockDao.getColor());
        assertEquals(block.getType(), blockDao.getType());
        assertEquals(block.getContainerImage(), blockDao.getContainerImage());
        assertEquals(block.getStatus(), blockDao.getStatus());
        assertEquals(block.getCommunicationParadigm(), blockDao.getCommunicationParadigm());
        assertEquals(block.getCreatedBy(), blockDao.getCreatedBy());
        assertEquals(block.getLastModifiedBy(), blockDao.getLastModifiedBy());
        assertEquals(block.getCreationDate(), blockDao.getCreationDate());
        assertEquals(block.getLastModifiedDate(), blockDao.getLastModifiedDate());
        assertEquals(block.getShutdownRelevant(), blockDao.getShutdownRelevant());

    }

    /**
     * Tests if the convert method correctly sets the shutdownRelevant flag in the BlockDao.
     */
    @Test
    public void testConvert_ShutdownRelevant() {

        // Arrange
        Block block = Block.builder().id("123").shutdownRelevant(true).build();

        // Act
        final var blockDao = this.blockMapper.convert(block);

        // Assert
        assertEquals(Boolean.TRUE, blockDao.getShutdownRelevant());
    }

}