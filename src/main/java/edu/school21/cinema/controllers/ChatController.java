package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequestMapping("/films/{filmId}/chat")
@Controller
public class ChatController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private MessageService messageService;

    @GetMapping()
    public String index(Model model, @PathVariable int filmId) {
        if (!filmService.isFilmRegistered(filmId))
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

        model.addAttribute("filmId", filmId);
        model.addAttribute("messages", messageService.getLastMessages(filmId));

        return "chat";
    }

    @MessageMapping("/films/{filmId}/chat/send")
    @SendTo("/films/{filmId}/chat/receive")
    public Message send(Message message) {
        messageService.saveMessage(message);
        return message;
    }
}
