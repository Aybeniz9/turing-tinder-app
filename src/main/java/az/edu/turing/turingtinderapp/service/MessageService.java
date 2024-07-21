package az.edu.turing.turingtinderapp.service;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.domain.repository.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final DAO<Message> messageDao;

    @Autowired
    public MessageService(@Qualifier("messageRepositoryDao") DAO<Message> messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getAllMessages() {
        return messageDao.getAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageDao.get(id);
    }

    public Message saveMessage(Message message) {
        if (message.getId() == null) {
            messageDao.insert(message);
        } else {
            messageDao.update(message);
        }
        return message;
    }

    public void deleteMessage(Long id) {
        messageDao.delete(id);
    }
}
