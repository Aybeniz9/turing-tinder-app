package az.edu.turing.turingtinderapp.model.dto;

import lombok.*;

import java.time.LocalDate;
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
