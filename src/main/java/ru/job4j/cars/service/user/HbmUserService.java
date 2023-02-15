package ru.job4j.cars.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.user.UserRepository;
import java.util.List;
import java.util.Optional;

/**
 * Класс - реализация сервиса пользователей
 * @see ru.job4j.cars.model.User
 */
@AllArgsConstructor
@Service
public class HbmUserService implements UserService {

    private final UserRepository repository;

    @Override
    public User create(User user) {
        return repository.create(user);
    }

    @Override
    public User update(User user) {
        return repository.update(user);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<User> findAllOrderById() {
        return repository.findAllOrderById();
    }

    @Override
    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findByLikeLogin(String key) {
        return repository.findByLikeLogin(key);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
