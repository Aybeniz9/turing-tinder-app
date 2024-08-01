package az.edu.turing.turingtinderapp.service;

import az.edu.turing.turingtinderapp.model.dto.MessageDto;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<MessageDto> getAllMessages();
    Optional<MessageDto> getMessageById(Long id);
    MessageDto insertMessage(MessageDto messageDto);
    void deleteMessageById(Long id);
    void updateMessage(MessageDto messageDto);
}

