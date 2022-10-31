package com.crm.mgr.bpm.lead.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LeadValidationDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(LeadValidationDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("isTrue", true);
        log.info("1");
    }
}
