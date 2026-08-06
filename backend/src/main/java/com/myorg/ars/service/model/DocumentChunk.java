package com.myorg.ars.service.model;

import java.util.Map;

public record DocumentChunk(String chunkId, String chunkText, int chunkIndex,DocumentMetadata metadata){
}
