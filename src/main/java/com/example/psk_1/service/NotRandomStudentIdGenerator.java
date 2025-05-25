package com.example.psk_1.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class NotRandomStudentIdGenerator implements IdGenerator {
    private int last = 200001;

    @Override
    public int generate() {
        System.out.println("NotRandomStudentIdGenerator");
        return last++;
    }
}
