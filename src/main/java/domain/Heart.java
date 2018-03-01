package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
//@Getter
//@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "heart.findByKweet", query = "SELECT h FROM Heart h WHERE h.kweet = :kweet"),
        @NamedQuery(name = "heart.findById", query = "SELECT h FROM Heart h WHERE h.id = :id")
})
public class Heart implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Date dateOfHearting;

    @ManyToOne
    private Kweet kweet;

    public Heart() {
    }

    public Heart(Date dateOfHearting, Kweet kweet) {
        this.dateOfHearting = dateOfHearting;
        this.kweet = kweet;
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public Date getDateOfHearting() {
        return dateOfHearting;
    }

    public void setDateOfHearting(Date dateOfHearting) {
        this.dateOfHearting = dateOfHearting;
    }

    public Kweet getKweet() {
        return kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }
    //</editor-fold>

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
        final Heart other = (Heart) o;
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
