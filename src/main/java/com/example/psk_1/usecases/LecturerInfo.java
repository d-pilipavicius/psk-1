package com.example.psk_1.usecases;

import com.example.psk_1.entities.Course;
import com.example.psk_1.entities.Lecturer;
import com.example.psk_1.persistence.CourseDAO;
import com.example.psk_1.persistence.LecturerDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class LecturerInfo implements Serializable {
    @Inject
    private LecturerDAO lecturerDAO;

    @Inject
    private CourseDAO courseDAO;

    @Getter @Setter
    private Lecturer lecturer;

    @Getter @Setter
    private Course course = new Course();

    @PostConstruct
    public void init() {
        Map<String, String> params =
            FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("lecturerId"));
        lecturer = lecturerDAO.get(id);
    }

    @Transactional
    public void createCourse() {
        course.setLecturer(lecturer);
        courseDAO.create(course);
    }
}
