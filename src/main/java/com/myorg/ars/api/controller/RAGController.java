package com.myorg.ars.api.controller;

import com.myorg.ars.service.DocumentOrchestrator;
import com.myorg.ars.service.model.Document;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RAGController {

    private final DocumentOrchestrator service;

    @PostMapping(value = "/documents", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadDocument(@RequestPart("doc") MultipartFile doc){
        log.info("Received Document:{}", doc.getOriginalFilename());
        if(doc==null ||doc.isEmpty()){
            log.error("File is empty");
            return  ResponseEntity.badRequest().body("Uploaded file is empty");
        }
        //TODO design how to not move the actual document in to service domain.
        Document document = new Document(UUID.randomUUID(),doc);
        log.info("Sending File to Decider Service");

        try {
            service.process(document);
        }
        catch (Exception e){
            log.error("Failed while sending to decider service",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed ot process document");

        }
        //TODO implement further endpoints, as this will eventually be async processing
        return ResponseEntity.status(HttpStatus.CREATED).header("X-JOB-ID", document.getJobID().toString())
                .body("File Successfully received");
    }
}
