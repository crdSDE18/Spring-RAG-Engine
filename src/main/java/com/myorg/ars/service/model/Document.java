package com.myorg.ars.service.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record Document(UUID jobID, MultipartFile doc) {
}