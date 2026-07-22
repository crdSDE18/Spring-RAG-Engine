package com.myorg.ars.service.ingestion;

import com.myorg.ars.data.entity.JobEntity;
import com.myorg.ars.data.repository.job.JobRepository;
import com.myorg.ars.exception.JobNotFoundException;
import com.myorg.ars.service.model.job.Job;
import com.myorg.ars.service.model.job.JobStatus;
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
    public Job createJob(UUID jobId){
        Job job = Job.create(jobId);
        jobRepository.save(job);

        return job;
    }


}
