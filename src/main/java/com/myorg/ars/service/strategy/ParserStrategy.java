package com.myorg.ars.service.strategy;

import com.myorg.ars.service.model.Document;

public interface ParserStrategy {
    /**
     *
     * @param contentType
     * @return
     */
    boolean supports(String contentType);
    String parse(Document document);
}
