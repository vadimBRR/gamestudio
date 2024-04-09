package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "comment", uniqueConstraints = @UniqueConstraint(columnNames = {"game","player"}))
public class Comment implements Serializable {
    @Id
    @GeneratedValue
    private int ident;

    @NonNull
    private String game;
    @NonNull
    private String player;
    @NonNull
    private String comment;
    @NonNull
    @Column(name = "commentedon")
    private Date commentedOn;


    @Override
    public String toString() {
        return "game: " + game + ", player: " + player + ", comment: " + comment + ", commentedOn: " + commentedOn;

    }

}
