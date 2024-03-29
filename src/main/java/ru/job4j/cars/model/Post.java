package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель данных объявления
 */
@Entity
@Table(name = "auto_post")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    /**
     * Идентификатор объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * Текст объявления
     */
    private String text;

    /**
     * Дата и время создания объявления
     */
    private LocalDateTime created = LocalDateTime.now();

    /**
     * Пользователь, создавший объявление
     */
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;

    /**
     * История изменения цен по объявлению
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_post_id")
    private List<PriceHistory> prices = new ArrayList<>();

    /**
     * Продаваемый автомобиль
     */
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    /**
     * Список пользователей, подписанных на объявление
     */
    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = { @JoinColumn(name = "auto_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "auto_post_id") }
    )
    private List<User> participates = new ArrayList<>();

    /**
     * Фото автомобиля
     */
    private byte[] photo;
}
