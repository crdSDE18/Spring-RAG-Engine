package com.myorg.ars.service.ingestion;

import com.myorg.ars.data.entity.EmbeddedChunkEntity;
import com.myorg.ars.data.repository.jpa.EmbeddingChunkRepositoryImpl;
import com.myorg.ars.service.strategy.model.DocumentChunk;
import com.myorg.ars.service.strategy.model.DocumentRequest;
import com.myorg.ars.service.strategy.model.EmbeddedChunk;
import com.myorg.ars.service.strategy.parser.ParserStrategy;
import com.myorg.ars.service.strategy.model.ParsedDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentOrchestrator {
    private final List<ParserStrategy> parserStrategies;
    private final RagChunkService chunkService;

    private final EmbedService embedService;
    private final EmbeddingChunkRepositoryImpl embeddingChunkRepository;


    public void processDocument(DocumentRequest documentRequest){

        //Step 1: decide the parser
        ParserStrategy parserStrategy = parserDecider(documentRequest.doc().getContentType());

        //Step 2: Parse the document
        ParsedDocument parsedDocument = parserStrategy.parse(documentRequest);

        //Step 3: Chunk document string to smaller chunks to be embedded
        List<DocumentChunk> documentChunks = chunkService.chunk(parsedDocument);

        //Step 4: Embed chunked documents
        List<EmbeddedChunk> embeddedChunks = embedService.embedDocument(documentChunks);

        embeddingChunkRepository.saveAll(embeddedChunks);
        log.info("successfully logged embedded chunks with job id: {}", embeddedChunks.get(0).jobId());



    }

    private ParserStrategy parserDecider(String mimeType){
        log.info("The mimetype for parsed document is: {} ", mimeType);
        return parserStrategies.stream()
                .filter(parserStrategy -> parserStrategy.supports(mimeType))
                .findFirst().orElseThrow(() -> new UnsupportedOperationException(
                        "Unsupported mime type: " + mimeType));

    }

    private List<EmbeddedChunkEntity> convert(List<EmbeddedChunk> embeddedChunks){
        return embeddedChunks.stream().map(eb ->
                EmbeddedChunkEntity.builder().jobId(eb.jobId()).chunk_text(eb.chunkText()).embedding(eb.vector()).
                        metadata(eb.metadata().toString()).build())
                .collect(Collectors.toList());
    }
}
