package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.Like;
import az.edu.turing.turingtinderapp.model.dto.LikeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);
    LikeDto toDto(Like like);
    Like toEntity(LikeDto likeDto);
    List<LikeDto> toDtoList(List<Like> likes);
    List<Like> toEntityList(List<LikeDto> likeDtos);
}
