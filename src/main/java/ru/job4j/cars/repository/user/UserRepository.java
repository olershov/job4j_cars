package ru.job4j.cars.repository.user;

import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Хранилище пользователей
 * @see ru.job4j.cars.model.User
 */
public interface UserRepository {

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return boolean результат сохранения.
     */
    public boolean create(User user);

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     * @return boolean результат обновления.
     */
    public boolean update(User user);

    /**
     * Удалить пользователя по id.
     * @param userId ID
     * @return boolean результат удаления.
     */
    public boolean delete(int userId);

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById();

    /**
     * Найти пользователя по ID
     * @param userId ID
     * @return Optional of user.
     */
    public Optional<User> findById(int userId);

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
