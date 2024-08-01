package az.edu.turing.turingtinderapp.service.impl;

import az.edu.turing.turingtinderapp.domain.entity.Like;
import az.edu.turing.turingtinderapp.domain.repository.LikeRepository;
import az.edu.turing.turingtinderapp.model.dto.LikeDto;
import az.edu.turing.turingtinderapp.model.mapper.LikeMapper;
import az.edu.turing.turingtinderapp.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private static final Logger logger = LoggerFactory.getLogger(LikeServiceImpl.class);
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper = LikeMapper.INSTANCE;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public List<LikeDto> getAllLikes() {
        logger.info("Fetching all likes");
        List<Like> likes = likeRepository.findAll();
        return likes.stream()
                .map(likeMapper::toDto)
                .toList();
    }

    @Override
    public Optional<LikeDto> getLikeById(Long id) {
        logger.info("Fetching like with id: {}", id);
        return likeRepository.findById(id)
                .map(likeMapper::toDto);
    }

    @Override
    public void saveLike(LikeDto likeDto) {
        logger.info("Saving like: {}", likeDto);
        Like like = likeMapper.toEntity(likeDto);
        likeRepository.save(like);
        logger.info("Like saved successfully");
    }

    @Override
    public void deleteLikeById(Long id) {
        logger.info("Deleting like with id: {}", id);
        likeRepository.deleteById(id);
        logger.info("Like deleted successfully");
    }

    @Override
    public void updateLike(Long id, LikeDto likeDto) {
        logger.info("Updating like with id: {}", id);
        if (likeRepository.existsById(id)) {
            Like like = likeMapper.toEntity(likeDto);
            like.setId(id);
            likeRepository.save(like);
            logger.info("Like updated successfully");
        } else {
            logger.error("Like not found with id: {}", id);
            throw new RuntimeException("Like not found with id: " + id);
        }
    }
}

