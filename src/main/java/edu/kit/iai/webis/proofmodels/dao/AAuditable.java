/*
 * Copyright (c) 2025-2026
 * Karlsruhe Institute of Technology - Institute for Automation and Applied Informatics (IAI)
 */
package edu.kit.iai.webis.proofmodels.dao;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

import static lombok.ToString.Include;

/**
 * AAuditable is an abstract base class designed to provide auditing capabilities
 * for entities in a persistence context. This class facilitates the automatic
 * tracking of metadata such as creation and modification details for the entities
 * extending it. It uses JPA and Spring Data auditing annotations to automatically populate these fields during entity persistence and update operations.
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 189435748237483L;

    /**
     * Represents the user who created the entity. The value is automatically
     * populated by the auditing framework during the creation of the entity.
     */
    @Include
    @CreatedBy
    public String createdBy;

    /**
     * Stores information about the user who last modified the entity.
     * This field is automatically populated by the auditing mechanism during entity update operations.
     * It typically references the identifier or details of the user responsible for the last modification.
     */
    @Include
    @LastModifiedBy
    public String lastModifiedBy;

    /**
     * Represents the timestamp when the entity was created.
     * This field is automatically populated when the entity is persisted.
     */
    @Include
    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public Instant creationDate;

    /**
     * Represents the timestamp of the last modification made to the entity.
     * This field is automatically updated whenever the entity is updated
     * in the persistence context.
     */
    @Include
    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public Instant lastModifiedDate;

}
