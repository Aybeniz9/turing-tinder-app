package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.model.dto.MessageDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-21T17:28:03+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDto toDto(Message message) {
        if ( message == null ) {
            return null;
        }

        Long id = null;
        Long senderId = null;
        Long receiverId = null;
        String content = null;
        LocalDate date = null;

        MessageDto messageDto = new MessageDto( id, senderId, receiverId, content, date );

        return messageDto;
    }

    @Override
    public Message toEntity(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        Message message = new Message();

        return message;
    }

    @Override
    public List<MessageDto> toDtoList(List<Message> messages) {
        if ( messages == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( messages.size() );
        for ( Message message : messages ) {
            list.add( toDto( message ) );
        }

        return list;
    }

    @Override
    public List<Message> toEntityList(List<MessageDto> messageDtos) {
        if ( messageDtos == null ) {
            return null;
        }

        List<Message> list = new ArrayList<Message>( messageDtos.size() );
        for ( MessageDto messageDto : messageDtos ) {
            list.add( toEntity( messageDto ) );
        }

        return list;
    }
}
