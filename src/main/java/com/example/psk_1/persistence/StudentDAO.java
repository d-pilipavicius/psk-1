package com.example.psk_1.persistence;

import com.example.psk_1.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StudentDAO {
    @Inject
    private EntityManager em;

    public List<Student> findAll() { return em.createNamedQuery("Student.findAll", Student.class).getResultList(); }
    public List<Integer> findAllStudentId() { return em.createNamedQuery("Student.findAllStudentId", Integer.class).getResultList(); }
    public void create(Student student) { em.persist(student); }
    public Student get(int id) { return em.find(Student.class, id); }
    public Student update(Student student) { return em.merge(student); }
}
