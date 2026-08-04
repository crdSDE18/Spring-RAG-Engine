package com.myorg.ars.api.controller;

import com.myorg.ars.service.ingestion.DocumentOrchestrator;
import com.myorg.ars.service.ingestion.JobService;
import com.myorg.ars.service.model.DocumentMetadata;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RAGController {

    private final DocumentOrchestrator orchestrator;
    private final JobService service;

    @PostMapping(value = "/documents", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadDocument(@RequestPart("doc") MultipartFile doc) {
        log.info("Received Document:{}", doc.getOriginalFilename());
        //TODO domain error handling
        if (doc == null || doc.isEmpty()){
            throw new RuntimeException("Uploaded file is missing or empty");
        }

        InputStream inputStream;
        try {
           inputStream = doc.getInputStream();
        }
        catch (IOException e){
            log.error("Failed while reading input stream", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed while reading input stream");
        }

        UUID jobId;
        //TODO domain error handling
        try {
            jobId = service.createJob();
        } catch(Exception e){
            log.error("Failed while creating job", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed while creating job");
        }

        DocumentMetadata metadata = new DocumentMetadata(doc.getOriginalFilename(), doc.getSize(), doc.getContentType(),new HashMap<>());
        DocumentRequest request = new DocumentRequest(jobId,inputStream,metadata);

        log.info("Sending File to orchestrator");
        try {
            orchestrator.processDocument(request);
        } catch (Exception e) {
            log.error("Failed to process document", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process document");

        }
        //TODO implement further endpoints, as this will eventually be async processing
        return ResponseEntity.status(HttpStatus.CREATED).header("X-JOB-ID", jobId.toString())
                .body("File Successfully saved with job-id in header");
    }




}
