package com.example.psk_1.decorators;

import com.example.psk_1.service.IdGenerator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public abstract class GeneratorDecorator implements IdGenerator {
    @Inject
    @Delegate
    private IdGenerator idGenerator;

    @Override
    public int generate() {
        System.out.println("40000 added.");
        return idGenerator.generate()+0000;
    }
}
