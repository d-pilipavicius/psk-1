package com.example.psk_1.usecases.mybatis;

import com.example.psk_1.mybatis.dao.StudentMapper;
import com.example.psk_1.mybatis.model.Student;
import com.example.psk_1.service.IdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.mybatis.cdi.Transactional;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class MyBatisStudents {
    @Inject
    private StudentMapper studentMapper;

    @Inject
    private IdGenerator studentIdGenerator;

    @Getter
    @Setter
    private Student student = new Student();

    @Getter
    private List<Student> students;

    @PostConstruct
    public void init() { students = studentMapper.selectAll(); }

    @Transactional
    public void createStudent() { student.setStudentId(studentIdGenerator.generate()); studentMapper.insert(student); }
}
