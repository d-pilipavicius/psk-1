package com.example.psk_1.usecases;

import com.example.psk_1.entities.Lecturer;
import com.example.psk_1.persistence.LecturerDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Lecturers {
    @Inject
    private LecturerDAO lecturerDAO;

    @Getter @Setter
    private Lecturer lecturer = new Lecturer();

    @Getter
    private List<Lecturer> lecturers;

    @PostConstruct
    public void init() { lecturers = lecturerDAO.findAll(); }

    @Transactional
    public void createLecturer() { lecturerDAO.create(lecturer); }
}
