package az.edu.turing.turingtinderapp.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MESSAGES")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "sender_id", nullable = false)
    Long senderId;

    @Column(name = "receiver_id", nullable = false)
    Long receiverId;

    @Column(name = "content", nullable = false, length = 500)
    String content;

    @Column(name = "date", nullable = false)
    LocalDate date;
}
