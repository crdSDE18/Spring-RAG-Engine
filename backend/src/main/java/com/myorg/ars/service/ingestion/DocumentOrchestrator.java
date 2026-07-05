package com.myorg.ars.service.ingestion;

import com.myorg.ars.service.strategy.model.DocumentChunk;
import com.myorg.ars.service.strategy.model.DocumentRequest;
import com.myorg.ars.service.strategy.model.EmbeddedChunk;
import com.myorg.ars.service.strategy.parser.ParserStrategy;
import com.myorg.ars.service.strategy.model.ParsedDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentOrchestrator {
    private final List<ParserStrategy> parserStrategies;
    private final RagChunkService chunkService;

    private final EmbedService embedService;

    public void processDocument(DocumentRequest documentRequest){
        //TODO remove void and add a record type for testing
        //Step 1: decide the parser
        ParserStrategy parserStrategy = parserDecider(documentRequest.doc().getContentType());

        //Step 2: Parse the document
        ParsedDocument parsedDocument = parserStrategy.parse(documentRequest);

        //Step 3: Chunk document string to smaller chunks to be embedded
        List<DocumentChunk> documentChunks = chunkService.chunk(parsedDocument);

        //Step 4: Embed chunked documents
        List<EmbeddedChunk> embeddedChunks = embedService.embedDocument(documentChunks);

    }

    private ParserStrategy parserDecider(String mimeType){
        log.info("The mimetype for parsed document is: {} ", mimeType);
        return parserStrategies.stream()
                .filter(parserStrategy -> parserStrategy.supports(mimeType))
                .findFirst().orElseThrow(() -> new UnsupportedOperationException(
                        "Unsupported mime type: " + mimeType));

    }
}
