package com.crm.mgr.service.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NonNull Exception ex, @Nullable Object body,
            @NonNull HttpHeaders headers, HttpStatus status, @NonNull WebRequest request) {
        if (status.is5xxServerError()) {
            log.error("An exception occured, which will cause a {} response", status, ex);
        } else if (status.is4xxClientError()){
            log.warn("An exception occured, which will cause a {} response", status, ex);
        } else {
            log.debug("An exception occured, which will cause a {} response", status, ex);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
