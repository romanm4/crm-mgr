package com.crm.mgr.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = {"com.crm.mgr", "com.crm.mgr.bpm.lead.delegate"})
@EntityScan(basePackages = {"com.crm.mgr.entity"})
@EnableJpaRepositories("com.crm.mgr.repo")
@EnableConfigurationProperties
public class CrmMgrApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmMgrApplication.class, args);
    }
}
