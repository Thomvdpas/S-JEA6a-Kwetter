package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
@Entity
public class Kweet implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1, max = 280)
    private String messageBody;

    private UserProfile sender;

    @OneToMany
    private List<Heart> hearts;

    @OneToMany
    private List<UserProfile> mentions;

    @OneToMany
    private List<Hashtag> hashtags;

    public Kweet() {
    }

    public Kweet(String messageBody, UserProfile sender, List<Heart> hearts, List<UserProfile> mentions, List<Hashtag> hashtags) {
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

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public UserProfile getSender() {
        return sender;
    }

    public void setSender(UserProfile sender) {
        this.sender = sender;
    }

    public List<Heart> getHearts() {
        return hearts;
    }

    public void setHearts(List<Heart> hearts) {
        this.hearts = hearts;
    }

    public List<UserProfile> getMentions() {
        return mentions;
    }

    public void setMentions(List<UserProfile> mentions) {
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
