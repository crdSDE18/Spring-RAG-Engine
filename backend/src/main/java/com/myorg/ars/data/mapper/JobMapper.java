package com.myorg.ars.data.mapper;

import com.myorg.ars.data.entity.JobEntity;
import com.myorg.ars.data.entity.JobStatus;
import com.myorg.ars.service.model.job.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobMapper {

    public JobEntity toEntity(Job job){
        return JobEntity.builder()
                .jobId(job.jobId())
                .status(JobStatus.valueOf(job.status().name()))
                .createdAt(job.createdAt())
                .updatedAt(job.updatedAt())
                .build();
    }
}
