package com.myorg.ars.service.model.job;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JobStatus {

    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    RETRYING("RETRYING");

    private final String status;
}
