package com.example.psk_1.service;

import com.example.psk_1.persistence.StudentDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class RandomStudentIdGenerator {
    @Inject
    private StudentDAO studentDAO;

    public int generate() {
        int random = 200000 + (int)(Math.random()*100000);
        List<Integer> studentIds = studentDAO.findAllStudentId();
        while(studentIds.contains(random))
            random = 200000 + (int)(Math.random()*100000);

        return random;
    }
}
