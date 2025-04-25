package com.example.psk_1.persistence;

import com.example.psk_1.entities.Course;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CourseDAO {
    @Inject
    private EntityManager em;

    public void create(Course course) { em.persist(course); }
    public Course get(int id) { return em.find(Course.class, id); }
    public Course update(Course course) { return em.merge(course); }
}
