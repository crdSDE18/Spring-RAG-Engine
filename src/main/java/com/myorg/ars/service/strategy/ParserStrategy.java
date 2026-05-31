package com.myorg.ars.service.strategy;

import com.myorg.ars.service.model.Document;

public interface ParserStrategy {

    /**
     * This method abstracts the parsing functionality that will be used by different types of parsers for file content types
     * @param document
     * @return
     */
    String parse(Document document);
}
