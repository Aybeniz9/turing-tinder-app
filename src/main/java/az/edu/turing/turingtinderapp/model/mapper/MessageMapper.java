package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.model.dto.MessageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDto toDto(Message message);
    Message toEntity(MessageDto messageDto);
    List<MessageDto> toDtoList(List<Message> messages);
    List<Message> toEntityList(List<MessageDto> messageDtos);
}
