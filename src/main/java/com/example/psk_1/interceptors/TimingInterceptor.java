package com.example.psk_1.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Timed
@Interceptor
public class TimingInterceptor {
    @AroundInvoke
    public Object measureMethodExecutionTime(InvocationContext context) throws Exception {
        long start = System.nanoTime();

        try {
            return context.proceed();  // execute the method
        } finally {
            long end = System.nanoTime();
            long durationInMs = (end - start) / 1_000_000;
            System.out.println(context.getMethod().getDeclaringClass().getSimpleName() + "." +
                    context.getMethod().getName() + " executed in " + durationInMs + " ms");
        }
    }
}