package com.example.psk_1.usecases.mybatis;

import com.example.psk_1.mybatis.model.Lecturer;
import com.example.psk_1.mybatis.dao.LecturerMapper;
import lombok.Getter;
import lombok.Setter;
import org.mybatis.cdi.Transactional;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class MyBatisLecturers {
    @Inject
    private LecturerMapper lecturerMapper;

    @Getter
    @Setter
    private Lecturer lecturer = new Lecturer();

    @Getter
    private List<Lecturer> lecturers;

    @PostConstruct
    public void init() { lecturers = lecturerMapper.selectAll(); }

    @Transactional
    public void createLecturer() { lecturerMapper.insert(lecturer); }
}
