package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="messages")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private int id;

    @Getter
    @Setter
    @Column(name="film_id")
    private int filmId;

    @Getter
    @Setter
    @Column(name="send_time")
    private long sendTime;

    @Getter
    @Setter
    private String senderId;

    @Getter
    @Setter
    private String body;
}
