package az.edu.turing.turingtinderapp.service.impl;
import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.domain.repository.MessageRepository;
import az.edu.turing.turingtinderapp.model.dto.MessageDto;
import az.edu.turing.turingtinderapp.model.mapper.MessageMapper;
import az.edu.turing.turingtinderapp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messageMapper.toDto(messages);
    }

    @Override
    public Optional<MessageDto> getMessageById(Long id) {
        return messageRepository.findById(id)
                .map(messageMapper::toDto);
    }

    @Override
    public MessageDto insertMessage(MessageDto messageDto) {
        Message message = messageMapper.toEntity(messageDto);
        Message savedMessage = messageRepository.save(message);
        logger.info("Message created with ID: {}", savedMessage.getId());
        return messageMapper.toDto(savedMessage);
    }

    @Override
    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
        logger.info("Message deleted with ID: {}", id);
    }

    @Override
    public void updateMessage(MessageDto messageDto) {
        Message message = messageMapper.toEntity(messageDto);
        messageRepository.save(message);
        logger.info("Message updated with ID: {}", message.getId());
    }
    @Override
    public List<MessageDto> getMessagesByUserId(Long userId) {
        logger.info("Fetching messages for user with id: {}", userId);
        List<Message> messages = messageRepository.findBySenderId(userId);
        return messages.stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> getMessagesBetweenUsers(Long senderId, Long receiverId) {
        logger.info("Fetching messages between sender id: {} and receiver id: {}", senderId, receiverId);
        List<Message> messages = messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        return messages.stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateMessageContent(Long messageId, String newContent) {
        logger.info("Updating content of message with id: {}", messageId);
        Optional<Message> messageOpt = messageRepository.findById(messageId);
        if (messageOpt.isPresent()) {
            Message message = messageOpt.get();
            message.setContent(newContent);
            messageRepository.save(message);
            logger.info("Message content updated successfully");
        } else {
            logger.error("Message not found with id: {}", messageId);
            throw new RuntimeException("Message not found with id: " + messageId);
        }
    }

    @Override
    public void deleteMessagesByUserId(Long userId) {
        logger.info("Deleting messages for user with id: {}", userId);
        messageRepository.deleteBySenderId(userId);
        logger.info("Messages deleted successfully");
    }

    @Override
    public List<MessageDto> getMessagesByDate(LocalDate date) {
        logger.info("Fetching messages for date: {}", date);
        List<Message> messages = messageRepository.findByDate(date);
        return messages.stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void markMessageAsRead(Long messageId) {
        // Implement according to your application's requirements
    }

    @Override
    public void markMessagesAsRead(Long userId) {
        // Implement according to your application's requirements
    }
}

