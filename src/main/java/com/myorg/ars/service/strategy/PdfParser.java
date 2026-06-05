package com.myorg.ars.service.strategy;

import com.myorg.ars.service.strategy.model.DocumentRequest;
import com.myorg.ars.service.strategy.model.ParsedDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.management.ManagementPermission;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PdfParser implements ParserStrategy {

    @Override
    public boolean supports(String contentType) {
        String APPLICATION_PDF = "application/pdf";
        return contentType.equals(APPLICATION_PDF);

    }

    @Override
    public ParsedDocument parse(DocumentRequest documentRequest) {
        try{
            PDDocument pdDocument = Loader.loadPDF(documentRequest.doc().getBytes());

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("article start", pdfTextStripper.getArticleStart());
            metadata.put("article end" , pdfTextStripper.getArticleEnd());
            metadata.put("page start", pdfTextStripper.getPageStart());
            metadata.put("page end" , pdfTextStripper.getEndPage());
            metadata.put("doc Id", documentRequest.jobID());
            metadata.put("file name" , documentRequest.doc().getOriginalFilename());

            log.info("METADATA: {}", metadata);


            ParsedDocument parsedDocument = new ParsedDocument(documentRequest.jobID().toString(),
                    pdfTextStripper.getText(pdDocument),metadata);

            log.info("parsed document: {}", parsedDocument);
            return parsedDocument;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
