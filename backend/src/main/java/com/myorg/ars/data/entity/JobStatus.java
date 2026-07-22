package com.myorg.ars.data.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JobStatus {

    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    RETRYING("RETRYING");

    private final String status;
}
