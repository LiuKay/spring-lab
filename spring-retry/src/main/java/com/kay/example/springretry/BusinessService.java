package com.kay.example.springretry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class BusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    private RetryTemplate retryTemplate;

    @Retryable(maxAttempts = 4, value = BusinessException.class, backoff = @Backoff(delay = 500),
            recover = "recover")
    public void doSomething() {
        LOGGER.info("do some logic...");

        throw new BusinessException("failed invoke....");
    }

    @Recover
    public void recover(BusinessException exception) {
        LOGGER.info("invoke recover....");
        LOGGER.info("get ex:{}", exception.getMessage());
    }

    public void useTemplateRetry() {
        retryTemplate.execute(arg -> {
            doSomething();
            return null;
        });
    }
}
