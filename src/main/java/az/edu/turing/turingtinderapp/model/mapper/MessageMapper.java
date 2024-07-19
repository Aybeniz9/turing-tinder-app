package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.model.dto.MessageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDto toDto(Message message);
    Message toEntity(MessageDto messageDto);
}
