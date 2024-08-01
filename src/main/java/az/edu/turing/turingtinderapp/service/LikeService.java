package az.edu.turing.turingtinderapp.service;

import java.util.List;
import java.util.Optional;

import az.edu.turing.turingtinderapp.model.dto.LikeDto;


public interface LikeService {

    List<LikeDto> getAllLikes();

    Optional<LikeDto> getLikeById(Long id);

    void saveLike(LikeDto likeDto);

    void deleteLikeById(Long id);

    void updateLike(Long id, LikeDto likeDto);
}


//@Service
//public class LikeService {
//    private final LikeRepository likeRepository;
//
//    @Autowired
//    public LikeService(LikeRepository likeRepository) {
//        this.likeRepository = likeRepository;
//    }
//
//    public List<Like> getAllLikes() {
//        return likeRepository.findAll();
//    }
//
//    public Optional<Like> getLikeById(Long id) {
//        return likeRepository.findById(id);
//    }
//
//    public void saveLike(Like like) {
//        likeRepository.save(like);
//    }
//
//    public void deleteLikeById(Long id) {
//        likeRepository.deleteById(id);
//    }
//
//    public void update(Like like) {
//        likeRepository.save(like);
//    }
//    public Like insert(Like like) {
//        return likeRepository.save(like); // Save the like object and return the saved instance
//    }
//    public Like updateLike(Long id, Like like) {
//        if (likeRepository.existsById(id)) {
//            like.setId(id);
//            return likeRepository.save(like);
//        }
//        throw new IllegalArgumentException("Like with ID " + id + " does not exist.");
//    }
//
//}
//public List<Like> getBy(Predicate<Like> predicate) {
//    return likeRepository.getBy(predicate);
// }