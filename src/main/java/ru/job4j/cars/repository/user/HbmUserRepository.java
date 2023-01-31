package ru.job4j.cars.repository.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Класс - реализация хранилища пользователей
 * @see ru.job4j.cars.model.User
 */
@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {

    private final CrudRepository crudRepository;
    private static final String BY_ID = " WHERE id = :fId";
    private static final String DELETE = "DELETE User" + BY_ID;
    private static final String FIND_ALL = "from User";
    private static final String LIKE_LOGIN = " where login LIKE :fKey";
    private static final String BY_LOGIN = " where login = :fLogin";

    @Override
    public User create(User user) {
        crudRepository.run(session -> session.persist(user));
        return user;
    }

    @Override
    public User update(User user) {
        crudRepository.run(session -> session.merge(user));
        return user;
    }

    @Override
    public void delete(int id) {
        crudRepository.run(DELETE, Map.of("fId", id));
    }

    @Override
    public List<User> findAllOrderById() {
        return crudRepository.query(FIND_ALL, User.class);
    }

    @Override
    public Optional<User> findById(int id) {
        return crudRepository.optional(
                FIND_ALL + BY_ID, User.class,
                Map.of("fId", id)
        );
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                FIND_ALL + LIKE_LOGIN, User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return crudRepository.optional(
                FIND_ALL + BY_LOGIN, User.class,
                Map.of("fLogin", login)
        );
    }
}
