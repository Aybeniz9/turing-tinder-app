package az.edu.turing.turingtinderapp.controller;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MessageDto>> getMessagesByUserId(@PathVariable Long userId) {
        List<MessageDto> messages = messageService.getMessagesByUserId(userId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/between/{senderId}/{receiverId}")
    public ResponseEntity<List<MessageDto>> getMessagesBetweenUsers(@PathVariable Long senderId, @PathVariable Long receiverId) {
        List<MessageDto> messages = messageService.getMessagesBetweenUsers(senderId, receiverId);
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{id}/content")
    public ResponseEntity<Void> updateMessageContent(@PathVariable Long id, @RequestParam String content) {
        messageService.updateMessageContent(id, content);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteMessagesByUserId(@PathVariable Long userId) {
        messageService.deleteMessagesByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<MessageDto>> getMessagesByDate(@PathVariable LocalDate date) {
        List<MessageDto> messages = messageService.getMessagesByDate(date);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/{id}/read")
    public ResponseEntity<Void> markMessageAsRead(@PathVariable Long id) {
        messageService.markMessageAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/{userId}/read")
    public ResponseEntity<Void> markMessagesAsRead(@PathVariable Long userId) {
        messageService.markMessagesAsRead(userId);
        return ResponseEntity.noContent().build();
    }
}

