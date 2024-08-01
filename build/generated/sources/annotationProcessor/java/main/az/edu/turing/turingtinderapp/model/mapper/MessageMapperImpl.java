package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.Message;
import az.edu.turing.turingtinderapp.model.dto.MessageDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-01T17:18:46+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDto toDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDto.MessageDtoBuilder messageDto = MessageDto.builder();

        messageDto.date( message.getDate() );
        messageDto.id( message.getId() );
        messageDto.senderId( message.getSenderId() );
        messageDto.receiverId( message.getReceiverId() );
        messageDto.content( message.getContent() );

        return messageDto.build();
    }

    @Override
    public Message toEntity(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        Message message = new Message();

        message.setDate( messageDto.date() );
        message.setId( messageDto.id() );
        message.setSenderId( messageDto.senderId() );
        message.setReceiverId( messageDto.receiverId() );
        message.setContent( messageDto.content() );

        return message;
    }

    @Override
    public List<MessageDto> toDto(List<Message> messages) {
        if ( messages == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( messages.size() );
        for ( Message message : messages ) {
            list.add( toDto( message ) );
        }

        return list;
    }
}
