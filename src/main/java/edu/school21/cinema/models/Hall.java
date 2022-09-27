package edu.school21.cinema.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class Hall {
    @NonNull private int serialNumber;
    @NonNull private int numberOfSeats;
}
