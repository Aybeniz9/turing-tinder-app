package az.edu.turing.turingtinderapp.service;



import az.edu.turing.turingtinderapp.domain.entity.Like;
import az.edu.turing.turingtinderapp.domain.repository.LikeRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class LikeService {
    private final LikeRepositoryDao likeRepositoryDao;

    @Autowired
    public LikeService(LikeRepositoryDao likeRepositoryDao) {
        this.likeRepositoryDao = likeRepositoryDao;
    }

    public List<Like> getAll() {
        return likeRepositoryDao.getAll();
    }

    public List<Like> getBy(Predicate<Like> predicate) {
        return likeRepositoryDao.getBy(predicate);
    }

    public Optional<Like> get(Long id) {
        return likeRepositoryDao.get(id);
    }

    public void insert(Like like) {
        likeRepositoryDao.insert(like);
    }

    public void delete(Long id) {
        likeRepositoryDao.delete(id);
    }

    public void update(Like like) {
        likeRepositoryDao.update(like);
    }
}