package ru.job4j.cars.repository.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.config.TestHbmConfig;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

 public class HbmUserRepositoryTest {

     private final TestHbmConfig testHbmConfig = new TestHbmConfig();
     private final CrudRepository crudRepository = testHbmConfig.getCrudRepository();
     private final HbmUserRepository userRepository = new HbmUserRepository(crudRepository);

     @AfterEach
     public void wipeTable() {
         testHbmConfig.wipeTable("DELETE from User");
     }

     @Test
     public void whenCreateAndFindById() {
         User user = new User(0, "login", "password");
         userRepository.create(user);
         User result = userRepository.findById(user.getId()).get();
         assertThat(result.getLogin()).isEqualTo(user.getLogin());
     }

     @Test
     public void whenUpdate() {
         User user = new User(0, "login", "password");
         user = userRepository.create(user);
         user.setLogin("updatedLogin");
         userRepository.update(user);
         User result = userRepository.findById(user.getId()).get();
         assertThat(result.getLogin()).isEqualTo(user.getLogin());
     }

     @Test
     public void whenDelete() {
         User user = new User(0, "login", "password");
         userRepository.create(user);
         userRepository.delete(user.getId());
         Optional<User> result = userRepository.findById(user.getId());
         assertThat(result).isEmpty();
     }

     @Test
     public void whenFindAllAndFindByLoginAndFindByLikeLogin() {
         User user = new User(0, "login", "password");
         User user2 = new User(0, "login2", "password2");
         User user3 = new User(0, "user", "password3");
         userRepository.create(user);
         userRepository.create(user2);
         userRepository.create(user3);
         assertEquals(userRepository.findAllOrderById(), List.of(user, user2, user3));
         assertEquals(userRepository.findByLogin("user").get(), user3);
         assertEquals(userRepository.findByLikeLogin("ogi"), List.of(user, user2));
     }
}