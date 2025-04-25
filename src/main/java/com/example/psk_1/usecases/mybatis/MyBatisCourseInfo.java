package com.example.psk_1.usecases.mybatis;

import com.example.psk_1.mybatis.dao.CourseMapper;
import com.example.psk_1.mybatis.dao.StudentCourseMapper;
import com.example.psk_1.mybatis.dao.StudentMapper;
import com.example.psk_1.mybatis.model.Course;
import com.example.psk_1.mybatis.model.Student;
import com.example.psk_1.mybatis.model.StudentCourse;
import lombok.Getter;
import lombok.Setter;
import org.mybatis.cdi.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class MyBatisCourseInfo implements Serializable {
    @Inject
    private CourseMapper courseMapper;

    @Inject
    private StudentMapper studentMapper;

    @Inject
    private StudentCourseMapper studentCourseMapper;

    @Getter @Setter
    private Course course;

    @Getter
    private List<Student> addedStudents;

    @Getter
    private List<Student> nonAddedStudents;

    @PostConstruct
    public void init() {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("courseId"));
        course = courseMapper.selectByPrimaryKey(id);

        initLists();
    }

    @Transactional
    public void addStudentToCourse() {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int studentId = Integer.parseInt(params.get("studentId"));
        Student student = studentMapper.selectByPrimaryKey(studentId);

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourselistId(course.getId());
        studentCourse.setStudentlistId(student.getId());

        studentCourseMapper.insert(studentCourse);

        initLists();
    }

    @Transactional
    public void removeStudentFromCourse() {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int studentId = Integer.parseInt(params.get("studentId"));
        Student student = studentMapper.selectByPrimaryKey(studentId);

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourselistId(course.getId());
        studentCourse.setStudentlistId(student.getId());

        studentCourseMapper.delete(studentCourse);

        initLists();
    }

    private void initLists() {
        addedStudents = studentCourseMapper.getAllCourseStudents(course.getId());

        nonAddedStudents = studentMapper.selectAll()
                .stream()
                .filter(student -> !addedStudents.contains(student))
                .collect(Collectors.toList());
    }
}
