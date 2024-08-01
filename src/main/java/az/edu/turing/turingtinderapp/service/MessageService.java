package az.edu.turing.turingtinderapp.service;

import az.edu.turing.turingtinderapp.model.dto.MessageDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<MessageDto> getAllMessages();
    Optional<MessageDto> getMessageById(Long id);
    MessageDto insertMessage(MessageDto messageDto);
    void deleteMessageById(Long id);
    void updateMessage(MessageDto messageDto);
    List<MessageDto> getMessagesByUserId(Long userId);

    List<MessageDto> getMessagesBetweenUsers(Long senderId, Long receiverId);

    void updateMessageContent(Long messageId, String newContent);

    void deleteMessagesByUserId(Long userId);

    List<MessageDto> getMessagesByDate(LocalDate date);

    void markMessageAsRead(Long messageId);

    void markMessagesAsRead(Long userId);
}

