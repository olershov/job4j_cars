package ru.job4j.cars.repository;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс, описывающий хранилище объектов, с методами CRUD
 */
public interface Repository<T> {

    /**
     * Сохранить в базе.
     * @param t сохраняемый объект.
     * @return T сохраненный объект.
     */
    public T create(T t);

    /**
     * Обновить в базе объект.
     * @param t обновляемый объект.
     * @return T обновлённый объект.
     */
    public T update(T t);

    /**
     * Удалить объект по id.
     * @param id ID
     */
    public void delete(int id);

    /**
     * Список объектов отсортированных по id.
     * @return список объектов T.
     */
    public List<T> findAllOrderById();

    /**
     * Найти объект по ID
     * @param id ID
     * @return Optional of T.
     */
    public Optional<T> findById(int id);

}
