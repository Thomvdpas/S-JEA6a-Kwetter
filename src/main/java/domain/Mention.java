package domain;

import java.io.Serializable;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public class Mention implements Serializable{

    private Long id;

    private Kweet kweet;
    private User receiver;

    public Mention() {}

    public Mention(Kweet kweet, User receiver) {
        this.kweet = kweet;
        this.receiver = receiver;
    }
}
