package com.example.psk_1.rest.mappers;

import com.example.psk_1.entities.Student;
import com.example.psk_1.rest.contracts.StudentDTO;
import com.example.psk_1.rest.contracts.UpdateStudentDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentMapper {
    public StudentDTO toStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setStudentId(student.getStudentId());

        return studentDTO;
    }

    public Student toStudent(StudentDTO studentDTO) {
        Student student = new Student();

        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setStudentId(studentDTO.getStudentId());

        return student;
    }

    public Student fromUpdateToStudent(Integer id, UpdateStudentDto studentDTO) {
        Student student = new Student();

        student.setId(id);
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setStudentId(studentDTO.getStudentId());

        return student;
    }
}
