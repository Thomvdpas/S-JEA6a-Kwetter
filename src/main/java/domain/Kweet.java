package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Getter
@Setter
@Entity
public class Kweet implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1, max = 280)
    private String messageBody;

    @ManyToOne
    private UserProfile sender;

    @OneToMany
    private List<Heart> hearts;

    @OneToMany
    private List<Mention> mentions;

    @OneToMany
    private List<Hashtag> hashtags;

    public Kweet() {
    }

    public Kweet(String messageBody, UserProfile sender, List<Heart> hearts, List<Mention> mentions, List<Hashtag> hashtags) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.hearts = hearts;
        this.mentions = mentions;
        this.hashtags = hashtags;
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
        final Kweet other = (Kweet) o;
        return Objects.equals(this.sender, other.sender);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }
    //</editor-fold>
}
