package az.edu.turing.turingtinderapp.model.mapper;

import az.edu.turing.turingtinderapp.domain.entity.Like;
import az.edu.turing.turingtinderapp.model.dto.LikeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    LikeDto toDto(Like like);
    Like toEntity(LikeDto likeDto);
}
