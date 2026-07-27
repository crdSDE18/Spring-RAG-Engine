package com.myorg.ars.service.model.job;

import java.time.Instant;
import java.util.UUID;

public record Job(UUID jobId, JobStatus status, Instant createdAt, Instant updatedAt){

    public static Job create(){
        return new Job(UUID.randomUUID(),JobStatus.PENDING,Instant.now(),Instant.now());
    }
}
