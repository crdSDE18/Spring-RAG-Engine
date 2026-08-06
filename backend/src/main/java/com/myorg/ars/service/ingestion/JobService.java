package com.myorg.ars.service.ingestion;


import com.myorg.ars.data.repository.job.JobRepository;
import com.myorg.ars.service.model.job.Job;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class JobService {

    private final JobRepository jobRepository;

    //TODO retry logic
    @Transactional
    public UUID createJob() {
        Job job = Job.create();
        jobRepository.save(job);
        log.info("successfully saved job with job id: {} ", job.jobId());
        return job.jobId();
    }
}
