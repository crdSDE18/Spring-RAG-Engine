package com.myorg.ars.service.strategy.model;

import java.util.Map;

public record ParsedDocument(String docId, String parsedText, Map<String, Object> metadata) {
}
