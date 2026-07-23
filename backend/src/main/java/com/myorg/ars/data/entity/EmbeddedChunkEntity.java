package com.myorg.ars.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="embedded_chunks")
public class EmbeddedChunkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="job_id", nullable = false)
    private String jobId;

    @Column(name="chunk_text",nullable = false,columnDefinition ="TEXT")
    private String chunk_text;

    @Column(name="embedding",nullable = false,columnDefinition ="vector(768)")
    private float[] embedding;

    @Column(name="metadata")
    private String metadata;

    //TODO createdAt

}
