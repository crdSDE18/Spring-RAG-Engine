package com.myorg.ars.config;

import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RAGEngineAppConfig {

    @Bean("token")
    public TokenTextSplitter tokenTextSplitter(ChunkingConfig config) {

        return TokenTextSplitter.builder()
                .withChunkSize(config.getChunkSize())
                .withKeepSeparator(config.isKeepSeparator())
                .withMinChunkLengthToEmbed(config.getMinChunkSize())
                .withMaxNumChunks(config.getMaxNumChunkSize())
                .build();
    }
}
