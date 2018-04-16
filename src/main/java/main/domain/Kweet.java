package main.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Thom van de Pas on 27-2-2018
 */
//@Getter
//@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "kweet.findByBodyText", query = "SELECT K FROM Kweet k WHERE k.messageBody LIKE :bodyText"),
        @NamedQuery(name = "kweet.findByAccount", query = "SELECT k FROM Kweet k WHERE k.sender = :sender"),
        @NamedQuery(name = "kweet.findByMention", query = "SELECT k FROM Kweet k WHERE :mention MEMBER OF k.mentions"),
        @NamedQuery(name = "kweet.findByFollowings",
                query = "SELECT k FROM Kweet k WHERE k.sender IN :followings"),
        @NamedQuery(name = "kweet.findMyKweetsOrderedByDate",
                query = "SELECT k FROM Kweet k WHERE k.sender = :sender ORDER BY k.timeOfPosting DESC")
})
public class Kweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 140)
    private String messageBody;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date timeOfPosting;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    private Profile sender;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts;

    @OneToMany
    private List<Profile> mentions;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    private List<Hashtag> hashtags;

    public Kweet() {
        this.timeOfPosting = new Date();
        this.hashtags = new ArrayList<>();
        this.mentions = new ArrayList<>();
        this.hearts = new ArrayList<>();
    }

    public Kweet(@Size(max = 140) String messageBody, Profile sender) {
        this();
        this.messageBody = messageBody;
        this.sender = sender;
    }

    public JsonObject toJson() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        JsonArrayBuilder hashtagArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder heartsArrayBuilder = Json.createArrayBuilder();

        for (Hashtag hashtag : hashtags) {
            hashtagArrayBuilder.add(hashtag.toJson());
        }

        for (Heart heart : hearts) {
            heartsArrayBuilder.add(heart.toJson());
        }


        return Json.createObjectBuilder()
                .add("messageBody", this.messageBody)
                .add("hearts", heartsArrayBuilder)
                .add("date", dateFormat.format(this.timeOfPosting))
                .add("hashtags", hashtagArrayBuilder)
                .add("sender", this.sender.toJson())
                .build();
    }

    public void addHashtag(Hashtag hashtag) {
        this.hashtags.add(hashtag);
    }

    public void addHeart(Heart heart) {
        this.hearts.add(heart);
    }

    public void addMention(Profile mention) {
        this.mentions.add(mention);
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

    public Date getTimeOfPosting() {
        return timeOfPosting;
    }

    public void setTimeOfPosting(Date timeOfPosting) {
        this.timeOfPosting = timeOfPosting;
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
