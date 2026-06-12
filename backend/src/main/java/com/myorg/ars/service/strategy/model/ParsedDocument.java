package com.myorg.ars.service.strategy.model;

import java.util.Map;

public record ParsedDocument(String jobID, String parsedText, Map<String, Object> metadata) {
}
