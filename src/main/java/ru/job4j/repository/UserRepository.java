package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.model.User;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Integer id = (Integer) session.save(user);
            session.getTransaction().commit();
            user.setId(id);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET login = :fLogin, password = :fPassword WHERE id = :fId")
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        Query query = session.createQuery("from User");
        return query.getResultList();
    }

    /**
     * Найти пользователя по ID
     * @param userId ID
     * @return Optional of user.
     */
    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                "from User as u where u.id = :fId", User.class);
        query.setParameter("fId", userId);
        User inDb = query.uniqueResult();
        return Optional.ofNullable(inDb);
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        Query query = session.createQuery(
                "from User as u where u.login LIKE :fKey", User.class);
        query.setParameter("fKey", "%" + key + "%");
        return query.getResultList();
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional of user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery(
                "from User as u where u.login = :fLogin", User.class);
        query.setParameter("fLogin", login);
        User inDB = query.uniqueResult();
        return Optional.ofNullable(inDB);
    }
}
