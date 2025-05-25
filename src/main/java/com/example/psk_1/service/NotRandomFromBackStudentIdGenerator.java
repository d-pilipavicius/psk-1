package com.example.psk_1.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

//@Specializes
@ApplicationScoped
public class NotRandomFromBackStudentIdGenerator extends NotRandomStudentIdGenerator {
    private int seed = 299999;

    @Override
    public int generate() {
        System.out.println("NotRandomFromBackStudentIdGenerator");
        return seed--;
    }
}
