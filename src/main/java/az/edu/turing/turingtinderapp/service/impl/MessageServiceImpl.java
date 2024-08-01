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

import java.util.List;
import java.util.Optional;

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
}

