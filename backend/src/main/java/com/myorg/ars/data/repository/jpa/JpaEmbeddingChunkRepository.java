package com.myorg.ars.data.repository.jpa;

import com.myorg.ars.data.entity.EmbeddedChunkEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface JpaEmbeddingChunkRepository extends JpaRepository<EmbeddedChunkEntity, UUID> {

}

