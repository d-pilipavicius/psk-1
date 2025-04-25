package com.example.psk_1.usecases;

import com.example.psk_1.entities.Student;
import com.example.psk_1.persistence.StudentDAO;
import com.example.psk_1.service.RandomStudentIdGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.transaction.Transactional;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Students {
    @Inject
    private StudentDAO studentDAO;

    @Inject
    private RandomStudentIdGenerator studentIdGenerator;

    @Getter @Setter
    private Student student = new Student();

    @Getter
    private List<Student> students;

    @PostConstruct
    public void init() { students = studentDAO.findAll(); }

    @Transactional
    public void createStudent() { student.setStudentId(studentIdGenerator.generate()); studentDAO.create(student); }
}
