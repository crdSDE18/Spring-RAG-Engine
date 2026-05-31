package com.myorg.ars.service;

import com.myorg.ars.service.model.Document;
import com.myorg.ars.service.strategy.ParserStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeciderService {
    private final Map<String, ParserStrategy> parserStrategyMap;

    public String decide(Document document){
        String contentType = document.getDocument().getContentType();
        log.info("Deciding parsing strategy for doc type: {}", contentType);
        return parserStrategyMap.get(contentType).parse(document);

    }
}
