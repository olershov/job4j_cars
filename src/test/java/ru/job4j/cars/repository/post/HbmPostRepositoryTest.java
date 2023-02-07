package ru.job4j.cars.repository.post;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.config.TestHbmConfig;
import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.car.HbmCarRepository;
import ru.job4j.cars.repository.driver.HbmDriverRepository;
import ru.job4j.cars.repository.engine.HbmEngineRepository;
import ru.job4j.cars.repository.user.HbmUserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class HbmPostRepositoryTest {

    private static final TestHbmConfig TEST_HBM_CONFIG = new TestHbmConfig();
    private static final CrudRepository CRUD_REPOSITORY = TEST_HBM_CONFIG.getCrudRepository();
    private static final HbmUserRepository USER_REPOSITORY = new HbmUserRepository(CRUD_REPOSITORY);
    private static final HbmDriverRepository DRIVER_REPOSITORY = new HbmDriverRepository(CRUD_REPOSITORY);
    private static final HbmEngineRepository ENGINE_REPOSITORY = new HbmEngineRepository(CRUD_REPOSITORY);
    private static final HbmCarRepository CAR_REPOSITORY = new HbmCarRepository(CRUD_REPOSITORY);
    private final  HbmPostRepository postRepository = new HbmPostRepository(CRUD_REPOSITORY);
    private static User user = new User(0, "login", "password");
    private static Driver driver = new Driver(0, "name", user);
    private static Engine engine = new Engine(0, "v18");
    private static Engine engine2 = new Engine(0, "m16");
    private static Car car = new Car(0, "BMW", "X5", engine, driver, new HashSet<>());
    private static Car car2 = new Car(0, "AUDI", "A8", engine2, driver, new HashSet<>());

    @BeforeAll
    public static void fillAssociatedTables() {
        user = USER_REPOSITORY.create(user);
        driver = DRIVER_REPOSITORY.create(driver);
        engine = ENGINE_REPOSITORY.create(engine);
        engine2 = ENGINE_REPOSITORY.create(engine2);
        car = CAR_REPOSITORY.create(car);
        car2 = CAR_REPOSITORY.create(car2);
    }

    @AfterEach
    public void wipeTable() {
        TEST_HBM_CONFIG.wipeTable("DELETE from Post");
    }

    @AfterAll
    public static void wipeAssociatedTables() {
        TEST_HBM_CONFIG.wipeTable("DELETE from Car");
        TEST_HBM_CONFIG.wipeTable("DELETE from Driver");
        TEST_HBM_CONFIG.wipeTable("DELETE from User");
        TEST_HBM_CONFIG.wipeTable("DELETE from Engine");
    }

    @Test
    public void whenCreateAndFindById() {
        Post post = new Post();
        post.setText("text");
        post.setUser(user);
        post.setCar(car);
        postRepository.create(post);
        Post result = postRepository.findById(post.getId()).get();
        assertThat(result.getText()).isEqualTo(post.getText());
    }

    @Test
    public void whenUpdate() {
        Post post = new Post();
        post.setText("text");
        post.setUser(user);
        post.setCar(car);
        postRepository.create(post);
        post.setText("updatedText");
        postRepository.update(post);
        Post result = postRepository.findById(post.getId()).get();
        assertThat(result.getText()).isEqualTo(post.getText());
    }

    @Test
    public void whenDelete() {
        Post post = new Post();
        post.setText("text");
        post.setUser(user);
        post.setCar(car);
        postRepository.create(post);
        postRepository.delete(post.getId());
        Optional<Post> result = postRepository.findById(post.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void whenFindAll() {
        Post post = new Post();
        post.setText("text");
        post.setUser(user);
        post.setCar(car);
        postRepository.create(post);
        Post post2 = new Post();
        post2.setText("text2");
        post2.setUser(user);
        post2.setCar(car2);
        postRepository.create(post2);
        assertEquals(postRepository.findAllOrderById(), List.of(post, post2));
    }

    @Test
    public void whenFindCreatedLastDay() {
        Post post = new Post();
        post.setText("text");
        post.setUser(user);
        post.setCar(car);
        postRepository.create(post);
        Post post2 = new Post();
        post2.setText("text2");
        post2.setUser(user);
        post2.setCar(car2);
        post2.setCreated(post2.getCreated().minusDays(2));
        postRepository.create(post2);
        assertEquals(postRepository.findCreatedLastDay(), List.of(post));
    }

    @Test
    public void whenFindWithPhoto() {
        Post post = new Post();
        post.setText("text");
        post.setUser(user);
        post.setCar(car);
        postRepository.create(post);
        Post post2 = new Post();
        post2.setText("text2");
        post2.setUser(user);
        post2.setCar(car2);
        post2.setPhoto(new byte[2]);
        postRepository.create(post2);
        assertEquals(postRepository.findWithPhoto(), List.of(post2));
    }

    @Test
    public void whenFindByBrand() {
        Post post = new Post();
        post.setText("text");
        post.setUser(user);
        post.setCar(car);
        postRepository.create(post);
        Post post2 = new Post();
        post2.setText("text2");
        post2.setUser(user);
        post2.setCar(car2);
        postRepository.create(post2);
        assertEquals(postRepository.findByBrand("BMW"), List.of(post));
    }
}

