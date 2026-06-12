package com.myorg.ars.service.strategy.model;

import java.util.Map;

public record DocumentChunk(String chunkId, String chunkText, int chunkIndex, Map<String,Object> metadata){
}
