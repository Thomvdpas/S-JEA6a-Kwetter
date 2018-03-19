package main.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Thom van de Pas on 16-3-2018
 */
@Entity
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;
    private String methodName;
    private String parameters;
    private Date time;

    public Log() {
    }

    public Log(String className, String methodName, String parameters) {
        this.time = new Date();
        this.className = className;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
