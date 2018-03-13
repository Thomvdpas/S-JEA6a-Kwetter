/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptor;

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
    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        String name = context.getMethod().getName();
        String params = Arrays.toString(context.getParameters());
        System.out.println("Logging: name = " +name + " params = "+ params);
        return context.proceed();
    }
    
}
