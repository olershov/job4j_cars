package ru.job4j.cars.repository.user;

import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Хранилище пользователей
 * @see ru.job4j.cars.model.User
 */
public interface UserRepository extends Repository<User> {

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key);

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional of user.
     */
    public Optional<User> findByLogin(String login);
}
