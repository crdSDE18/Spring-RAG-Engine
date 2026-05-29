package com.myorg.ars.service.strategy;

import com.myorg.ars.service.model.Document;

public interface ParserStrategy {

    void parse(Document document);
}
