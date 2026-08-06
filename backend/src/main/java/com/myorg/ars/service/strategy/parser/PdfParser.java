package com.myorg.ars.service.strategy.parser;

import com.myorg.ars.service.model.DocumentRequest;
import com.myorg.ars.service.model.ParsedDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

        try (InputStream input = documentRequest.content()){
            PDDocument pdDocument = Loader.loadPDF(input.readAllBytes());


            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String parsedText = pdfTextStripper.getText(pdDocument);

            ParsedDocument parsedDocument =
                    new ParsedDocument(String.valueOf(documentRequest.jobId()), parsedText,documentRequest.metadata());

            log.info("parsed document: {}", parsedDocument);
            return parsedDocument;


        } catch (Exception e) {
            log.error("Error while parsing pdf file",e);
            throw new RuntimeException(e);
        }

    }
}
