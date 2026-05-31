package com.myorg.ars.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Document {
    private UUID jobID;
    private MultipartFile document;
}
