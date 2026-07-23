package com.myorg.ars.service.strategy.parser;

import com.myorg.ars.service.model.DocumentRequest;
import com.myorg.ars.service.model.ParsedDocument;

public interface ParserStrategy {
    /**
     *
     * @param contentType
     * @return
     */
    boolean supports(String contentType);
    ParsedDocument parse(DocumentRequest documentRequest);
}
