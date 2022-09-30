package edu.school21.cinema.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="halls")
@NoArgsConstructor
public class Hall {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    @Column(name="serial_number")
    private int serialNumber;

    @Getter
    @Setter
    @Column(name="number_of_seats")
    private int numberOfSeats;

    public Hall(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
