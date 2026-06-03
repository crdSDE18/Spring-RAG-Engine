package com.myorg.ars.service;

import com.myorg.ars.service.model.Document;
import com.myorg.ars.service.strategy.ParserStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentOrchestrator {
    private final List<ParserStrategy> parserStrategies;

    public String process(Document document){

        String contentType = document.getDocument().getContentType();
        ParserStrategy parser = parserStrategies.stream()
                .filter(parserStrategy -> parserStrategy.supports(contentType))
                .findFirst().orElseThrow(() -> new UnsupportedOperationException(
                        "Unsupported mime type: " + contentType));


        //log
        return parser.parse(document);

    }
}
