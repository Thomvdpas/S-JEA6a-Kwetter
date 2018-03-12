package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
//@Getter
//@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "hashtag.findByBodyText", query = "SELECT h FROM Hashtag h WHERE h.bodyText = :bodyText")
})
public class Hashtag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bodyText;

    @ManyToOne(cascade = CascadeType.ALL)
    private Kweet kweet;

    public Hashtag() {
    }

    public Hashtag(String bodyText, Kweet kweet) {
        this.bodyText = bodyText;
        this.kweet = kweet;
    }

    //<editor-fold desc="Getters/Setters">
    public String getBodyText() {
        return bodyText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }
    //</editor-fold>
}
