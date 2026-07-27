package com.myorg.ars.service.model;


import java.util.HashMap;

public record DocumentMetadata(String originalFilename, long size, String mimetype, HashMap<String,Object> additionalProperties){}