package com.myorg.ars.service.model.job;

import java.time.Instant;
import java.util.UUID;

public record Job(UUID jobId, JobStatus status, Instant createdAt, Instant updatedAt){

    public static Job create(UUID jobId){
        return new Job(jobId,JobStatus.PENDING,Instant.now(),Instant.now());
    }
}
