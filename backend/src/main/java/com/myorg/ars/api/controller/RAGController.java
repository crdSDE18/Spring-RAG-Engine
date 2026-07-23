package com.myorg.ars.api.controller;

import com.myorg.ars.service.ingestion.DocumentOrchestrator;
import com.myorg.ars.service.ingestion.JobService;
import com.myorg.ars.service.model.DocumentRequest;
import com.myorg.ars.service.model.job.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RAGController {

    private final DocumentOrchestrator orchestrator;
    private final JobService service;

    @PostMapping(value = "/documents", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadDocument(@RequestPart("doc") MultipartFile doc) {
        log.info("Received Document:{}", doc.getOriginalFilename());
        Assert.notNull(doc, "No file present");
        Assert.isTrue(!doc.isEmpty(), "File is empty");

        UUID jobId = UUID.randomUUID();
        //TODO design how to not move the actual document in to service domain.
        DocumentRequest documentRequest = new DocumentRequest(jobId, doc);

        //TODO domain error handling
        try {
            service.createJob(jobId);
        } catch(Exception e){
            log.error("Failed while sending to decider service", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed while creating job");
        }

        log.info("Sending File to orchestrator");
        try {
            orchestrator.processDocument(documentRequest);
        } catch (Exception e) {
            log.error("Failed while sending to decider service", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process document");

        }

        //TODO implement further endpoints, as this will eventually be async processing
        return ResponseEntity.status(HttpStatus.CREATED).header("X-JOB-ID", jobId.toString())
                .body("File Successfully saved with job-id in header");
    }




}
