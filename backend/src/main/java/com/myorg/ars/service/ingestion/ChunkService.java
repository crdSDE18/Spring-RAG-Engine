package com.myorg.ars.service.ingestion;

import com.myorg.ars.service.model.DocumentChunk;
import com.myorg.ars.service.model.ParsedDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChunkService {

    private final TokenTextSplitter tokenTextSplitter;

    public List<DocumentChunk> chunk(ParsedDocument parsedDocument) {
        //TODO null check

        List<Document> documents;
        try {
            log.info("chunking parsed documents");
            documents = tokenTextSplitter.split(new Document(parsedDocument.parsedText()));
            log.info("chunking completed: {}", documents);
        } catch (Exception e) {
            throw new RuntimeException("failed to split parsed document: {}", e);
        }
        int[] index = {0};

        return documents.stream()
                .map(document -> convertDocumentChunk(document, parsedDocument, index[0]++))
                .toList();

    }

    //TODO combine metadata
    private DocumentChunk convertDocumentChunk(Document document, ParsedDocument parsedDocument, int index) {
        return new DocumentChunk(parsedDocument.jobID(), document.getText(), index, parsedDocument.metadata());
    }
}

