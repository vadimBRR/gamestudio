package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@NamedQuery( name = "Score.getTopScoresUniquePlayers",
        query = "SELECT s FROM Score s WHERE s.game = :game AND s.points = (SELECT MAX(s2.points) FROM Score s2 WHERE s2.player = s.player AND s2.game = s.game) GROUP BY s.ident, s.player,s.game, s.points,s.playedOn ORDER BY s.points DESC")
@NamedQuery( name = "Score.getTopScores",
        query = "SELECT s FROM Score s WHERE s.game=:game ORDER BY s.points DESC")
@NamedQuery( name = "Score.resetScores",
        query = "DELETE FROM Score")
//@Table(name = "score", uniqueConstraints = @UniqueConstraint(columnNames = {"game","player"}))

public class Score implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ident;
    @NonNull
    private String game;
    @NonNull
    private String player;
    @NonNull
    private int points;
    @NonNull
    @Column(name = "playedon")
    private Date playedOn;


    @Override
    public String toString() {
        return "game: " + game + ", player: " + player + ", points: " + points + ", playedOn: " + playedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return points == score.points && Objects.equals(game, score.game) && Objects.equals(player, score.player) && Objects.equals(playedOn.getTime(), score.playedOn.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, player, points, playedOn);
    }

}
