package com.myorg.ars.service;

import com.myorg.ars.service.model.Document;
import com.myorg.ars.service.strategy.ParserStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentOrchestrator {
    private final List<ParserStrategy> parserStrategies;

    public Document processDocument(Document document){
        //Step 1: decide the parser
        ParserStrategy parserStrategy = parserDecider(document.doc().getContentType());

        //Step 2: Parse the document
        String parsedDocument = parserStrategy.parse(document);

        //Step 3: Chunk document string to smaller chunks to be embedded


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
