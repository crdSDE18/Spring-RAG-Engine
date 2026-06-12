package com.myorg.ars;

import com.myorg.ars.config.ChunkingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ChunkingConfig.class)
@SpringBootApplication
public class SpringRagEngine {

    public static void main(String[] args){
        SpringApplication.run(SpringRagEngine.class);
    }

}
