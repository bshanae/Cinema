package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;

    @Transactional
    public List<Message> getLastMessages(int filmId) {
        return repository.getLastMessages(filmId);
    }

    @Transactional
    public void saveMessage(Message message) {
        repository.saveMessage(message);
    }
}
