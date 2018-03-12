package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
        @NamedQuery(name = "kweet.findByAccount", query = "SELECT k FROM Kweet k WHERE k.sender = :sender")
})
public class Kweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 140)
    private String messageBody;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Profile sender;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Heart> hearts;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Profile> mentions;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Hashtag> hashtags;

    public Kweet() {
    }

    public Kweet(String messageBody, Profile sender, List<Heart> hearts, List<Profile> mentions, List<Hashtag> hashtags) {
        this.messageBody = messageBody;
        this.sender = sender;
        this.hearts = hearts;
        this.mentions = mentions;
        this.hashtags = hashtags;
    }

    //<editor-fold desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Profile getSender() {
        return sender;
    }

    public void setSender(Profile sender) {
        this.sender = sender;
    }

    public List<Heart> getHearts() {
        return hearts;
    }

    public void setHearts(List<Heart> hearts) {
        this.hearts = hearts;
    }

    public List<Profile> getMentions() {
        return mentions;
    }

    public void setMentions(List<Profile> mentions) {
        this.mentions = mentions;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
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
