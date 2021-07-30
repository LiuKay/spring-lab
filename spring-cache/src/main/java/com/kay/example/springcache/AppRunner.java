package com.kay.example.springcache;

import com.kay.example.springcache.entity.Student;
import com.kay.example.springcache.repo.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public AppRunner(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        setUpData();

        log.info("query students...");
        log.info("Got: {}", studentRepository.getById("s1111"));
        log.info("Got: {}", studentRepository.getById("s2222"));
        log.info("Got: {}", studentRepository.getById("s3333"));

        log.info("query again");
        log.info("Got: {}", studentRepository.getById("s1111"));
        log.info("Got: {}", studentRepository.getById("s3333"));

        log.info("put s2222");
        studentRepository.put(new Student("s2222", "update s2222"));
        log.info("Got: {}", studentRepository.getById("s2222"));

    }

    private void setUpData() {
        List<Student> students = List.of(new Student("s1111", "s1 student"),
                                         new Student("s2222", "s2 student"),
                                         new Student("s3333", "s3 student"));

        students.forEach(studentRepository::put);
    }
}
