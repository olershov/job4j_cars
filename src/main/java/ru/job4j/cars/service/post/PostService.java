package ru.job4j.cars.service.post;

import ru.job4j.cars.model.Post;
import ru.job4j.cars.service.Service;

import java.util.List;

/**
 * Сервис объявлений
 * @see ru.job4j.cars.model.Post
 */
public interface PostService extends Service<Post> {

    /**
     * Список объявлений за последние сутки
     * @return список объявлений.
     */
    List<Post> findCreatedLastDay();

    /**
     * Список объявлений с фото
     * @return список объявлений.
     */
    List<Post> findWithPhoto();

    /**
     * Список объявлений по марке авто
     * @param key key
     * @return список объявлений.
     */
    List<Post> findByBrand(String key);

}
