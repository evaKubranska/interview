package com.epages.interview;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "interview")
@Data
public class InterviewApplicationConfig {

    private String allProductsUrl;
}
