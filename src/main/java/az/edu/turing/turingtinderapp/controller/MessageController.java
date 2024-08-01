package az.edu.turing.turingtinderapp.controller;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import az.edu.turing.turingtinderapp.model.dto.MessageDto;
import az.edu.turing.turingtinderapp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageDto>> getAllMessages() {
        List<MessageDto> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Long id) {
        Optional<MessageDto> messageDto = messageService.getMessageById(id);
        return messageDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto) {
        logger.info("Request received to create message: {}", messageDto);
        MessageDto createdMessage = messageService.insertMessage(messageDto);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMessage(@PathVariable Long id, @RequestBody MessageDto messageDto) {
        if (messageDto.id().equals(id)) {
            messageService.updateMessage(messageDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


//@RestController
//@RequestMapping("/messages")
//public class MessageController {
//
//    private final MessageService messageService;
//
//    @Autowired
//    public MessageController(MessageService messageService) {
//        this.messageService = messageService;
//    }
//
//    @GetMapping
//    public List<Message> getAllMessages() {
//        return messageService.getAllMessages();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
//        Optional<Message> message = messageService.getMessageById(id);
//        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public Message createMessage(@RequestBody Message message) {
//        return messageService.saveMessage(message);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
//        if (!messageService.getMessageById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        message.setId(id);
//        return ResponseEntity.ok(messageService.saveMessage(message));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
//        if (!messageService.getMessageById(id).isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        messageService.deleteMessage(id);
//        return ResponseEntity.noContent().build();
//    }


