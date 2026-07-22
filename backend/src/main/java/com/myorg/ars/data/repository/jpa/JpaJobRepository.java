package com.myorg.ars.data.repository.jpa;


import com.myorg.ars.data.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface JpaJobRepository extends JpaRepository<JobEntity, UUID> {
}
