package com.myorg.ars.data.repository.jpa;

import com.myorg.ars.data.entity.EmbeddedChunkEntity;
import com.myorg.ars.data.mapper.EmbeddingChunkMapper;
import com.myorg.ars.data.repository.EmbeddingChunkRepository;
import com.myorg.ars.service.strategy.model.EmbeddedChunk;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmbeddingChunkRepositoryImpl implements EmbeddingChunkRepository {

    private final EmbeddingChunkMapper embeddingChunkMapper;
    private final JpaEmbeddingChunkRepository repository;

    @Override
    public void saveAll(List<EmbeddedChunk> embeddedChunks) {
        List<EmbeddedChunkEntity> embeddedChunkEntities = embeddingChunkMapper.toEntity(embeddedChunks);
        repository.saveAll(embeddedChunkEntities);
    }

}
