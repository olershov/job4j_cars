package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.CrudRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс - реализация хранилища объявлений
 * @see ru.job4j.cars.model.Post
 */
@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {

    private final CrudRepository crudRepository;
    private static final String BY_ID = " WHERE id = :fId";
    private static final String CREATED_INTERVAL = " WHERE created  BETWEEN :fStart AND :fEnd";
    private static final String WITH_PHOTO = " WHERE photo is not null";
    private static final String DELETE = "DELETE Post" + BY_ID;
    private static final String FIND_ALL = "from Post";
    private static final String BY_BRAND = " p left join fetch p.car pc where pc.brand = :fBrand";

    @Override
    public Post create(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }

    @Override
    public Post update(Post post) {
        crudRepository.run(session -> session.merge(post));
        return post;
    }

    @Override
    public void delete(int id) {
        crudRepository.run(DELETE, Map.of("fId", id));
    }

    @Override
    public List<Post> findAllOrderById() {
        return crudRepository.query(
                FIND_ALL, Post.class
        );
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional(
                FIND_ALL + BY_ID, Post.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<Post> findCreatedLastDay() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(1);
        return crudRepository.query(FIND_ALL + CREATED_INTERVAL, Post.class,
        Map.of("fStart", start, "fEnd", end));
    }

    @Override
    public List<Post> findWithPhoto() {
        return crudRepository.query(FIND_ALL + WITH_PHOTO, Post.class);
    }

    @Override
    public List<Post> findByBrand(String key) {
        return crudRepository.query(
                FIND_ALL + BY_BRAND, Post.class, Map.of("fBrand", key)
        );
    }
}
