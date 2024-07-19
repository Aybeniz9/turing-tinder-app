package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.User;
import az.edu.turing.turingtinderapp.model.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<User> users);
    List<User> toEntityList(List<UserDto> userDtos);
}
