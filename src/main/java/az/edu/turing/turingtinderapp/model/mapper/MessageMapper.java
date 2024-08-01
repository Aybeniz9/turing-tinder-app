package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.model.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

//    @Mapping(target = "date", source = "date")
    MessageDto toDto(Message message);

//    @Mapping(target = "date",source = "date")
    Message toEntity(MessageDto messageDto);

    List<MessageDto> toDto(List<Message> messages);

    List<Message> toEntity(List<MessageDto> messageDtos);
}


