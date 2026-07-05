package com.myorg.ars.config;

import com.myorg.ars.service.ingestion.EmbedService;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RAGEngineAppConfig {

    @Bean
    public TokenTextSplitter tokenTextSplitter(ChunkingConfig config) {

        return TokenTextSplitter.builder()
                .withChunkSize(config.getChunkSize())
                .withKeepSeparator(config.isKeepSeparator())
                .withMinChunkLengthToEmbed(config.getMinChunkSize())
                .withMaxNumChunks(config.getMaxNumChunkSize())
                .build();
    }

}
