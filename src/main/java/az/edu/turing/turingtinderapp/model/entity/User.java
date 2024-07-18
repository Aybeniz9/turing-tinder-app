package az.edu.turing.turingtinderapp.model.entity;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String name;
    private String password;
    private String photoUrl;
    private String lastLogin;
}
