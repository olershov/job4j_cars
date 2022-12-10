package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Модель данных истории цен
 */
@Entity
@Table(name = "price_history")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PriceHistory {

    /**
     * Идентификатор истории цен
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * Цена до обновления
     */
    private int before;

    /**
     * Цена после обновления
     */
    private int after;

    /**
     * Время обновления цены
     */
    private LocalDateTime created;
}
