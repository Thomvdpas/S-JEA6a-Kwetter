package service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * @author Thom van de Pas on 27-2-2018
 */

@Singleton
@Startup
public class StartUp {

//    @Inject
//    private KweetService kweetService;

    public StartUp() {

    }

    @PostConstruct
    public void initData() {

    }
}
