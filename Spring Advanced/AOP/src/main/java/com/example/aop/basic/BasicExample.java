package com.example.aop.basic;

import com.example.aop.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BasicExample implements CommandLineRunner {

    private final Student student;

    public BasicExample(Student student) {
        this.student = student;
    }

    @Override
    public void run(String... args) throws Exception {

        student.sayHello();
        student.echo("ARGUMENT!");
        student.concat("A", "B");
        try {
            student.boom();
        } catch (Exception exception) {

        }

    }
}
