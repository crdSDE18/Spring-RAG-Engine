package com.myorg.ars.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "rag.chunking-config")
public class ChunkingConfig {
    private final int chunkSize;
    private final boolean keepSeparator;
    private final int minChunkSize;
    private final int maxNumChunkSize;
}
