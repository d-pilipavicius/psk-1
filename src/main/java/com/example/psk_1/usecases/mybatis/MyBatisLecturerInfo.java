package com.example.psk_1.usecases.mybatis;

import com.example.psk_1.mybatis.dao.CourseMapper;
import com.example.psk_1.mybatis.dao.LecturerMapper;
import com.example.psk_1.mybatis.model.Course;
import com.example.psk_1.mybatis.model.Lecturer;
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

@Named
@ViewScoped
public class MyBatisLecturerInfo implements Serializable {
    @Inject
    private LecturerMapper lecturerMapper;

    @Inject
    private CourseMapper courseMapper;

    @Getter @Setter
    private Lecturer lecturer;

    @Getter @Setter
    private Course course = new Course();

    @PostConstruct
    public void init() {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("lecturerId"));
        lecturer = lecturerMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void createCourse() {
        course.setLecturerId(lecturer.getId());
        courseMapper.insert(course);
    }

    public List<Course> getLecturerCourses() {
        return lecturerMapper.selectLecturerCourses(lecturer.getId());
    }
}
