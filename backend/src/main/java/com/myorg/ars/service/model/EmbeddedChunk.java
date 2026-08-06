package com.myorg.ars.service.model;


import java.util.Map;
//TODO own model
public record EmbeddedChunk(String jobId, DocumentMetadata metadata, float[] vector, String chunkText){}
