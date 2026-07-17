package com.myorg.ars.data.mapper;

import com.myorg.ars.data.entity.EmbeddedChunkEntity;
import com.myorg.ars.service.strategy.model.EmbeddedChunk;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@RequiredArgsConstructor
@Component
public class EmbeddingChunkMapper {

    public List<EmbeddedChunkEntity> toEntity(List<EmbeddedChunk> embeddedChunks) {
        return embeddedChunks.stream().map(eb -> EmbeddedChunkEntity.builder()
                .jobId(eb.jobId())
                .chunk_text(eb.chunkText())
                .embedding(eb.vector())
                .metadata(eb.metadata().toString())
                .build()).collect(Collectors.toList());
    }
}

