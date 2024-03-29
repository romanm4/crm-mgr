package com.crm.mgr.app.config;

import com.hazelcast.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config hazelCastConfig() {
        return new Config().setInstanceName("crm-mgr-hazelcast-instance");
    }
}
