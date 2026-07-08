package com.udemy.cruddemo.dao;

import com.udemy.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);
    Student findById(Integer id);
    List<Student> findAll();
    List<Student> findByLastName(String theLastName);
    List<Student> findByFirstAndLastName(String theFirstName, String theLastName);
    void update(Student theStudent);
    void delete(Integer id);
    int deleteAll();

}
