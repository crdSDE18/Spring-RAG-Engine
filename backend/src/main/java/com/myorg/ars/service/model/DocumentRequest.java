package com.myorg.ars.service.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

//TODO don't move multipart doc through non controller domains
public record DocumentRequest(UUID jobID, MultipartFile doc) {
}