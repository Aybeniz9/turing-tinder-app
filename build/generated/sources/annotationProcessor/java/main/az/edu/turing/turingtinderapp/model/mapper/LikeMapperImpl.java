package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.Like;
import az.edu.turing.turingtinderapp.model.dto.LikeDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-01T23:07:22+0400",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class LikeMapperImpl implements LikeMapper {

    @Override
    public LikeDto toDto(Like like) {
        if ( like == null ) {
            return null;
        }

        Long id = null;
        Long userId = null;
        Long likedUserId = null;
        Boolean reaction = null;

        LikeDto likeDto = new LikeDto( id, userId, likedUserId, reaction );

        return likeDto;
    }

    @Override
    public Like toEntity(LikeDto likeDto) {
        if ( likeDto == null ) {
            return null;
        }

        Like like = new Like();

        return like;
    }

    @Override
    public List<LikeDto> toDtoList(List<Like> likes) {
        if ( likes == null ) {
            return null;
        }

        List<LikeDto> list = new ArrayList<LikeDto>( likes.size() );
        for ( Like like : likes ) {
            list.add( toDto( like ) );
        }

        return list;
    }

    @Override
    public List<Like> toEntityList(List<LikeDto> likeDtos) {
        if ( likeDtos == null ) {
            return null;
        }

        List<Like> list = new ArrayList<Like>( likeDtos.size() );
        for ( LikeDto likeDto : likeDtos ) {
            list.add( toEntity( likeDto ) );
        }

        return list;
    }
}
