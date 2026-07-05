package com.myorg.ars.service.strategy.model;


import java.util.Map;

public record EmbeddedChunk(String jobId, Map<String, Object> metadata, float[] vector){}
