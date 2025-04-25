package com.example.psk_1.usecases;

import com.example.psk_1.entities.Course;
import com.example.psk_1.entities.Student;
import com.example.psk_1.persistence.CourseDAO;
import com.example.psk_1.persistence.StudentDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class CourseInfo implements Serializable {
    @Inject
    private CourseDAO courseDAO;

    @Inject
    private StudentDAO studentDAO;

    @Getter @Setter
    private Course course;

    @Getter
    private List<Student> students;

    @PostConstruct
    public void init() {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("courseId"));
        course = courseDAO.get(id);
        students = studentDAO.findAll()
                .stream()
                .filter(student -> !course.getStudentList().contains(student))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addStudentToCourse() {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int studentId = Integer.parseInt(params.get("studentId"));
        Student student = studentDAO.get(studentId);

        course.getStudentList().add(student);
        student.getCourseList().add(course);

        courseDAO.update(course);
        studentDAO.update(student);
        students = studentDAO.findAll()
                .stream()
                .filter(student1 -> !course.getStudentList().contains(student1))
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeStudentFromCourse() {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int studentId = Integer.parseInt(params.get("studentId"));
        Student student = studentDAO.get(studentId);

        student.getCourseList().removeIf(course1 -> course1.getId() == course.getId());
        course.getStudentList().removeIf(student1 -> student1.getId() == student.getId());

        courseDAO.update(course);
        studentDAO.update(student);
        students = studentDAO.findAll()
                .stream()
                .filter(student1 -> !course.getStudentList().contains(student1))
                .collect(Collectors.toList());
    }
}
