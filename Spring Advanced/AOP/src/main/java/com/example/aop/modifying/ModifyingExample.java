package com.example.aop.modifying;

import com.example.aop.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ModifyingExample implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyingExample.class);

    private final Student student;

    public ModifyingExample(Student student) {
        this.student = student;
    }

    @Override
    public void run(String... args) throws Exception {

        String result = student.concat("A", "B");

        // if there is no aspect we would expect - result => AB
        // if there is an aspect we would expect - result => ([A]-[B])
        LOGGER.info("The result from the around advice is: {}", result);
    }
}
