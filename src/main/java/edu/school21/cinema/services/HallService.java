package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HallService {
    @Autowired
    private HallRepository repository;

    @Transactional
    public List<Hall> getHalls() {
        return repository.getHalls();
    }

    @Transactional
    public void addHall(int numberOfSeats) {
        repository.addHall(new Hall(numberOfSeats));
    }
}
