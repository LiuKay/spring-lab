package com.kay.example.springretry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private BusinessService businessService;

    @Override
    public void run(String... args) throws Exception {
        businessService.doSomething();

        businessService.useTemplateRetry();
    }
}
