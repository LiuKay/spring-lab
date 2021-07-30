package com.kay.example.springcache.repo;

import com.kay.example.springcache.entity.Student;

public interface StudentRepository {

    Student getById(String studentId);

    void put(Student student);
}
