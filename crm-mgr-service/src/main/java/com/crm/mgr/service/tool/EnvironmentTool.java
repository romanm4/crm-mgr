package com.crm.mgr.service.tool;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnvironmentTool {
    private final Environment environment;

    public EnvironmentTool(Environment environment) {
        this.environment = environment;
    }

    public boolean isNotTestProfile() {
        List<String> profiles
                = Arrays.stream(environment.getActiveProfiles()).filter("test"::equals).collect(Collectors.toList());
        return profiles.isEmpty();
    }
}
