package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель данных пользователя
 */
@Entity
@Table(name = "auto_user")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    /**
     * Идентификатор пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Логин пользователя
     */
    @EqualsAndHashCode.Include
    private String login;

    /**
     * Пароль пользователя
     */
    private String password;

    /**
     * Список объявлений, на обновление цены которых подписан пользователь
     */
    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = { @JoinColumn(name = "auto_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "auto_post_id") }
    )
    private List<Post> participates = new ArrayList<>();
}
