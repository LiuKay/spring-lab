package com.kay.example.springcache.repo;

import com.kay.example.springcache.entity.Student;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentRepositoryImpl implements StudentRepository {

    private static final Map<String, Student> MOCK_DB = new ConcurrentHashMap<>();

    @Cacheable(value = "student")
    @Override
    public Student getById(String studentId) {
        log.info("search student by id:{}", studentId);
        mockSlowSearch();
        return MOCK_DB.get(studentId);
    }

    @CacheEvict(value = "student", key = "#student.id")
    @Override
    public void put(Student student) {
        MOCK_DB.put(student.getId(), student);
    }

    private void mockSlowSearch() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
