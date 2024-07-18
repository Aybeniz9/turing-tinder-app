package az.edu.turing.turingtinderapp.model.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int id;
    private int senderId;
    private int receiverId;
    private String content;
    private LocalDate date;
}
