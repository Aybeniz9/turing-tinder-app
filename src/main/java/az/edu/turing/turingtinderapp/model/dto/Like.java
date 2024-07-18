package az.edu.turing.turingtinderapp.model.dto;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Like {

    private int id;
    private int userId;
    private int likedUserId;
    private boolean reaction;
}
