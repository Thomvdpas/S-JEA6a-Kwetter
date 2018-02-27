package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "hashtag.findByBodyText", query = "SELECT h FROM Hashtag h WHERE h.bodyText = :bodyText")
})
public class Hashtag implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String bodyText;

    @ManyToOne
    private Kweet kweet;

    private Hashtag() {
    }

    private Hashtag(String bodyText, Kweet kweet) {
        this.bodyText = bodyText;
        this.kweet = kweet;
    }

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
