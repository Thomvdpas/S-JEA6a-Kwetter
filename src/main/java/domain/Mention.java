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
public class Mention implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Kweet kweet;

    @OneToOne
    private UserProfile receiver;

    public Mention() {
    }

    public Mention(Kweet kweet, UserProfile receiver) {
        this.kweet = kweet;
        this.receiver = receiver;
    }

    //<editor-fold desc="equals/hashCode">
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
        final Mention other = (Mention) o;
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
