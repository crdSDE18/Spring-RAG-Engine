package com.myorg.ars.service.strategy;

import com.myorg.ars.service.model.Document;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
    public String parse(Document document) {
        try{
            PDDocument pdDocument = Loader.loadPDF(document.getDocument().getBytes());

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            log.info("OUTPUT: {}", pdfTextStripper.getText(pdDocument));
            return pdfTextStripper.getText(pdDocument);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
