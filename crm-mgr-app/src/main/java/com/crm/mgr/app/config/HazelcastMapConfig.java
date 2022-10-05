package com.crm.mgr.app.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Configuration
public class HazelcastMapConfig {

    private final Config hazelcastConfig;

    public HazelcastMapConfig(Config hazelcastConfig) {
        this.hazelcastConfig = hazelcastConfig;
    }

    @PostConstruct
    public void initHazelcastConfig() {
        addMapConfig(hazelcastConfig, "address");
        addMapConfig(hazelcastConfig, "lead");
        addMapConfig(hazelcastConfig, "lead-status");
        addMapConfig(hazelcastConfig, "role");
        addMapConfig(hazelcastConfig, "task");
        addMapConfig(hazelcastConfig, "task-status");
        addMapConfig(hazelcastConfig, "todo-desc");
        addMapConfig(hazelcastConfig, "todo-type");
        addMapConfig(hazelcastConfig, "user");
        addMapConfig(hazelcastConfig, "user-status");
        addMapConfig(hazelcastConfig, "account");
    }

    private void addMapConfig(Config hazelcastConfig, String name) {
        MapConfig mapConfig = new MapConfig();
        mapConfig.setName(name);
        mapConfig.setTimeToLiveSeconds(3000);
        mapConfig.setEvictionConfig(new EvictionConfig()
                .setSize(200)
                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                .setEvictionPolicy(EvictionPolicy.LRU)
        );
        hazelcastConfig.addMapConfig(mapConfig);
    }
}
