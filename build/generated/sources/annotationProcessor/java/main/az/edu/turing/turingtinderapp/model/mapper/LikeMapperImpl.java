package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.Like;
import az.edu.turing.turingtinderapp.model.dto.LikeDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-01T17:18:45+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class LikeMapperImpl implements LikeMapper {

    @Override
    public LikeDto toDto(Like like) {
        if ( like == null ) {
            return null;
        }

        LikeDto.LikeDtoBuilder likeDto = LikeDto.builder();

        likeDto.id( like.getId() );
        likeDto.userId( like.getUserId() );
        likeDto.likedUserId( like.getLikedUserId() );
        likeDto.reaction( like.getReaction() );

        return likeDto.build();
    }

    @Override
    public Like toEntity(LikeDto likeDto) {
        if ( likeDto == null ) {
            return null;
        }

        Like like = new Like();

        like.setId( likeDto.id() );
        like.setUserId( likeDto.userId() );
        like.setLikedUserId( likeDto.likedUserId() );
        like.setReaction( likeDto.reaction() );

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
