package com.myorg.ars.service.model;

import java.util.Map;

public record ParsedDocument(String jobID, String parsedText, Map<String, Object> metadata) {
}
