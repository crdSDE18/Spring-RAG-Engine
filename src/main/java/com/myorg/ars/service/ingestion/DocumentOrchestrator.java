package com.myorg.ars.service.ingestion;

import com.myorg.ars.service.strategy.model.DocumentChunk;
import com.myorg.ars.service.strategy.model.DocumentRequest;
import com.myorg.ars.service.strategy.ParserStrategy;
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

    public DocumentRequest processDocument(DocumentRequest documentRequest){
        //Step 1: decide the parser
        ParserStrategy parserStrategy = parserDecider(documentRequest.doc().getContentType());

        //Step 2: Parse the document
        ParsedDocument parsedDocument = parserStrategy.parse(documentRequest);

        //Step 3: Chunk document string to smaller chunks to be embedded
        List<DocumentChunk> documentChunks = chunkService.chunk(parsedDocument);

        return null;
    }

    private ParserStrategy parserDecider(String mimeType){
        log.info("The mimetype for parsed document is: {} ", mimeType);
        return parserStrategies.stream()
                .filter(parserStrategy -> parserStrategy.supports(mimeType))
                .findFirst().orElseThrow(() -> new UnsupportedOperationException(
                        "Unsupported mime type: " + mimeType));


    }
}
