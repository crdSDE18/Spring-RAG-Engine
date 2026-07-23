package com.myorg.ars.data.repository.embedding;

import com.myorg.ars.service.model.EmbeddedChunk;

import java.util.List;


public interface EmbeddingChunkRepository {
    public void saveAll(List<EmbeddedChunk> embeddedChunks);
}
