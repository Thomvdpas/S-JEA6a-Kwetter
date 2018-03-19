/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.interceptor;

import main.domain.Log;
import main.service.LogService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

/**
 *
 * @author frankcoenen
 */
@Interceptor
@Logging
public class LoggingInterceptor {

    @Inject
    private LogService logService;

    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        String className = context.getMethod().getDeclaringClass().getName();
        String methodName = context.getMethod().getName();
        String parameters = Arrays.toString(context.getParameters());

        Log log = new Log(className, methodName, parameters);
        logService.save(log);

        return context.proceed();
    }
    
}
