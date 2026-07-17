package com.myorg.ars.data.repository;

import com.myorg.ars.service.strategy.model.EmbeddedChunk;

import java.util.List;


public interface EmbeddingChunkRepository {
    public void saveAll(List<EmbeddedChunk> embeddedChunks);
}
