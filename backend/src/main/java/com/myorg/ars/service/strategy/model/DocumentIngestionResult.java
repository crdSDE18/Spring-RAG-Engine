package com.myorg.ars.service.strategy.model;

import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.UUID;

public record DocumentIngestionResult(String jobId, String status, int chunkCount ) {
}
