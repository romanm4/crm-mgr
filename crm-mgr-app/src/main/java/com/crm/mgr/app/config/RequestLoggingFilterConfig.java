package com.crm.mgr.app.config;

import com.crm.mgr.service.log.CustomRequestLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestLoggingFilterConfig {
    @Bean
    public CustomRequestLoggingFilter logFilter() {
        CustomRequestLoggingFilter filter
                = new CustomRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        return filter;
    }
}
