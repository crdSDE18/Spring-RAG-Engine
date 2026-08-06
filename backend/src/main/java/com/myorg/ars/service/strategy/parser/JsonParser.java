package com.myorg.ars.service.strategy.parser;
import com.myorg.ars.service.model.DocumentRequest;
import com.myorg.ars.service.model.ParsedDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonParser implements ParserStrategy{
    @Override
    public boolean supports(String contentType) {
        String APPLICATION_JSON = "application/json";
        return contentType.equals(APPLICATION_JSON);

    }

    @Override
    public ParsedDocument parse(DocumentRequest documentRequest) {

        String parsedText = null;
        try(InputStream input = documentRequest.content()){
                parsedText = new String(input.readAllBytes(),StandardCharsets.UTF_8);

            return new ParsedDocument(String.valueOf(documentRequest.jobId()),parsedText,documentRequest.metadata());
        }
        catch (Exception e){
            log.error("ERROR while parsing json");
        }
        return new ParsedDocument(String.valueOf(documentRequest.jobId()),parsedText,documentRequest.metadata());
    }
}
