package ru.job4j.cars.repository.car;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.config.TestHbmConfig;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.driver.HbmDriverRepository;
import ru.job4j.cars.repository.engine.HbmEngineRepository;
import ru.job4j.cars.repository.user.HbmUserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class HbmCarRepositoryTest {

    private static final TestHbmConfig TEST_HBM_CONFIG = new TestHbmConfig();
    private static final CrudRepository CRUD_REPOSITORY = TEST_HBM_CONFIG.getCrudRepository();
    private static final HbmUserRepository USER_REPOSITORY = new HbmUserRepository(CRUD_REPOSITORY);
    private static final HbmDriverRepository DRIVER_REPOSITORY = new HbmDriverRepository(CRUD_REPOSITORY);
    private static final HbmEngineRepository ENGINE_REPOSITORY = new HbmEngineRepository(CRUD_REPOSITORY);
    private final HbmCarRepository carRepository = new HbmCarRepository(CRUD_REPOSITORY);
    private static User user = new User(0, "login", "password");
    private static Driver driver = new Driver(0, "name", user);
    private static Engine engine = new Engine(0, "v18");
    private static Engine engine2 = new Engine(0, "m16");

    @BeforeAll
    public static void fillAssociatedTables() {
        user = USER_REPOSITORY.create(user);
        driver = DRIVER_REPOSITORY.create(driver);
        engine = ENGINE_REPOSITORY.create(engine);
        engine2 = ENGINE_REPOSITORY.create(engine2);
    }

    @AfterEach
    public void wipeTable() {
        TEST_HBM_CONFIG.wipeTable("DELETE from Car");
    }

    @AfterAll
    public static void wipeAssociatedTables() {
        TEST_HBM_CONFIG.wipeTable("DELETE from Driver");
        TEST_HBM_CONFIG.wipeTable("DELETE from User");
        TEST_HBM_CONFIG.wipeTable("DELETE from Engine");
    }

    @Test
    public void whenCreateAndFindById() {
        Car car = new Car(0, "Mazda", "CX-7", engine, driver, new HashSet<>());
        carRepository.create(car);
        Car result = carRepository.findById(car.getId()).get();
        assertThat(result.getModel()).isEqualTo(car.getModel());
    }

    @Test
    public void whenUpdate() {
        Car car = new Car(0, "Mazda", "CX-7", engine, driver, new HashSet<>());
        carRepository.create(car);
        car.setModel("CX-9");
        carRepository.update(car);
        Car result = carRepository.findById(car.getId()).get();
        assertThat(result.getModel()).isEqualTo(car.getModel());
    }

    @Test
    public void whenDelete() {
        Car car = new Car(0, "Mazda", "CX-7", engine, driver, new HashSet<>());
        carRepository.create(car);
        carRepository.delete(car.getId());
        Optional<Car> result = carRepository.findById(car.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void whenFindAll() {
        Car car = new Car(0, "Mazda", "CX-7", engine, driver, new HashSet<>());
        Car car2 = new Car(0, "Suzuki", "SX-4", engine2, driver, new HashSet<>());
        carRepository.create(car);
        carRepository.create(car2);
        assertEquals(carRepository.findAllOrderById(), List.of(car, car2));
    }
}