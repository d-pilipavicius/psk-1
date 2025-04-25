package com.example.psk_1.persistence;

import com.example.psk_1.entities.Lecturer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class LecturerDAO {
    @Inject
    private EntityManager em;

    public void create(Lecturer lecturer) { em.persist(lecturer); }
    public Lecturer get(int id) { return em.find(Lecturer.class, id); }
    public Lecturer update(Lecturer lecturer) { return em.merge(lecturer); }
    public List<Lecturer> findAll() { return em.createNamedQuery("Lecturer.findAll", Lecturer.class).getResultList(); }
}
