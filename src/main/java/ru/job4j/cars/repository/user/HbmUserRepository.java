package ru.job4j.cars.repository.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {

    private final CrudRepository crudRepository;

    @Override
    public boolean create(User user) {
        boolean result = true;
        try {
            crudRepository.run(session -> session.save(user));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = true;
        try {
            crudRepository.run(
                    "UPDATE User SET login = :fLogin, password = :fPassword WHERE id = :fId",
                    Map.of("fLogin", user.getLogin(), "fPassword", user.getPassword(), "fId", user.getId())
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
            crudRepository.run("DELETE User WHERE id = :fId", Map.of("fId", id));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<User> findAllOrderById() {
        return crudRepository.query("from User", User.class);
    }

    @Override
    public Optional<User> findById(int id) {
        return crudRepository.optional(
                "from User where id = :fId", User.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                "from User where login LIKE :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(
                "from User where login = :fLogin", User.class,
                Map.of("fLogin", login)
        );
    }
}
