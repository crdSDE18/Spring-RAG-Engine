package com.myorg.ars.service.ingestion;


import com.myorg.ars.data.repository.jpa.EmbeddingChunkRepositoryImpl;
import com.myorg.ars.service.strategy.model.DocumentChunk;
import com.myorg.ars.service.strategy.model.EmbeddedChunk;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmbedService {

    private final EmbeddingModel embeddingModel;

    private final EmbeddingChunkRepositoryImpl embeddingChunkimplRepository;

    public List<EmbeddedChunk> embedDocument(List<DocumentChunk> documentChunks){
        Assert.notNull(documentChunks,"Chunk documents cannot be null");

        List<EmbeddedChunk> embeddedChunks = new ArrayList<>();
        try{

            for(DocumentChunk chunk: documentChunks){
                float[] vector = embeddingModel.embed(chunk.chunkText());

                EmbeddedChunk document =
                        new EmbeddedChunk(chunk.chunkId(),chunk.metadata(),vector, chunk.chunkText());

                embeddedChunks.add(document);
            }
            log.info("embedded chunk size: {}", embeddedChunks.size());
            return embeddedChunks;

        } catch (Exception e) {
            log.error("Fail while embedding document chunk",e);
            throw new RuntimeException(e);
        }

    }
}
