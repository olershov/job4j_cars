package ru.job4j.cars.repository.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.repository.CrudRepository;

@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {

    private final CrudRepository crudRepository;

}
