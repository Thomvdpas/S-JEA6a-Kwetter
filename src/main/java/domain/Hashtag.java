package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
public class Hashtag implements Serializable {

    private Long id;

    private String bodyText;
    private Kweet kweet;

    private Hashtag() {}

    private Hashtag(String bodyText, Kweet kweet){
        this.bodyText = bodyText;
        this.kweet = kweet;
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }
    //</editor-fold>

    //<editor-fold desc="hashCode/equals">
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final Hashtag other = (Hashtag) o;
        return Objects.equals(this.kweet, other.kweet);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }
    //</editor-fold>
}
