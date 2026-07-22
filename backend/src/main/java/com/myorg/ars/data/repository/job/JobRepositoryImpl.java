package com.myorg.ars.data.repository.job;

import com.myorg.ars.data.entity.JobEntity;
import com.myorg.ars.data.mapper.JobMapper;
import com.myorg.ars.data.repository.jpa.JpaJobRepository;
import com.myorg.ars.service.model.job.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JobRepositoryImpl implements JobRepository{

    private final JobMapper jobMapper;
    private final JpaJobRepository jobRepository;

    @Override
    public void save(Job job) {
        JobEntity jobEntity = jobMapper.toEntity(job);
        jobRepository.save(jobEntity);
    }

}
