package com.myorg.ars.service.model;


import java.util.Map;
//TODO own model
public record EmbeddedChunk(String jobId, Map<String, Object> metadata, float[] vector, String chunkText){}
