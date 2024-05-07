package sk.tuke.gamestudio.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
//@Table(name = "rating", uniqueConstraints = @UniqueConstraint(columnNames = {"game","player"}))
public class Rating implements Serializable {
    @Id
    @GeneratedValue
    private int ident;

    @NonNull
    private String game;
    @NonNull
    private String player;

    @NonNull
    @Column(columnDefinition = "integer")
    private int rating;

    @NonNull
    @Column(name = "ratedon")
    private Date ratedOn;

    @Override
    public String toString() {
        return "game: " + game + ", player: " + player + ", rating: " + rating + ", playedOn: " + ratedOn;
    }

}
