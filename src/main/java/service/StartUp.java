package service;

import domain.Hashtag;

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

    @Inject
    private HashtagService hashtagService;

    public StartUp() {

    }

    @PostConstruct
    public void initData() {
        Hashtag hashtag = new Hashtag();
        hashtag.setBodyText("Test");
        hashtagService.save(hashtag);
    }
}
