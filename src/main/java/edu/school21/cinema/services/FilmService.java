package edu.school21.cinema.services;

import org.springframework.stereotype.Service;

@Service
public class FilmService {
    public boolean isFilmRegistered(int filmId) {
        return filmId == 0 || filmId == 1; // TODO
    }
}
