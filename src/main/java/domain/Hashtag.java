package domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
//@Getter
//@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "hashtag.findByBodyText", query = "SELECT h FROM Hashtag h WHERE h.bodyText LIKE :bodyText")
})
public class Hashtag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bodyText;

    @OneToMany(cascade = CascadeType.ALL)
    @CascadeOnDelete
    @JsonManagedReference
    private List<Kweet> kweets;

    public Hashtag() {
    }

    public Hashtag(String bodyText) {
        this();
        this.bodyText = bodyText;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("bodyText", this.getBodyText())
                .build();
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

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
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
