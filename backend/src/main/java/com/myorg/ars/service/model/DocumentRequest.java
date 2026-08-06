package com.myorg.ars.service.model;

import java.io.InputStream;
import java.util.UUID;

public record DocumentRequest(UUID jobId, InputStream content,DocumentMetadata metadata) {
}