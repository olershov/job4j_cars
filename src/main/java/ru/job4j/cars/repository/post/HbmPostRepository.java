package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {

    private final CrudRepository crudRepository;

    @Override
    public boolean create(Post post) {
        boolean result = true;
        try {
            crudRepository.run(session -> session.save(post));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(Post post) {
        boolean result = true;
        try {
            crudRepository.run(
                    "UPDATE Post SET text = :fText, car_id = :fCarId WHERE id = :fId",
                    Map.of("fText", post.getText(), "fCarId", post.getCar().getId(), "fId", post.getId())
            );
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = true;
        try {
            crudRepository.run("DELETE Post WHERE id = :fId", Map.of("fId", id));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Post> findAllOrderById() {
        return crudRepository.query("from Post", Post.class);
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional(
                "from Post where id = :fId", Post.class,
                Map.of("fId", id)
        );
    }
}
