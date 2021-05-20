package com.wolox.training.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.wolox.training.models.Users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUserName_thenReturnUser() {
        Users user = new Users("stella", "Joel Stella", LocalDate.of(1995, 10, 28), new ArrayList<>());
        user.setPassword("123");
        entityManager.persist(user);

        Optional<Users> foundUser = userRepository.findByUserName("stella");

        assertThat(foundUser.get().getName()).isEqualTo("Joel Stella");
    }

    @Test
    public void whenFindByNameIgnoreCaseAndBirthDateBetween_thenReturnUser() {
        Users user = new Users("stella", "Joel Stella", LocalDate.of(1995, 10, 28), new ArrayList<>());
        user.setPassword("123");
        Users user2 = new Users("stella2", "Joel Stella2", LocalDate.of(1995, 10, 30), new ArrayList<>());
        user2.setPassword("123");
        entityManager.persist(user);
        entityManager.persist(user2);

        List<Users> users = userRepository
                .findByNameIgnoreCaseContainingAndBirthDateBetween("JoEl", LocalDate.of(1995, 10, 27),
                        LocalDate.of(1995, 10, 29));

        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void whenFindByNameIgnoreCaseAndBirthDateBetweenWithNullParams_thenReturnUser() {
        Users user = new Users("stella", "Joel Stella", LocalDate.of(1995, 10, 28), new ArrayList<>());
        user.setPassword("123");
        Users user2 = new Users("stella2", "Joel Stella2", LocalDate.of(1995, 10, 30), new ArrayList<>());
        user2.setPassword("123");
        entityManager.persist(user);
        entityManager.persist(user2);

        List<Users> users = userRepository.findByNameIgnoreCaseContainingAndBirthDateBetween("JoEl", null, null);

        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void whenFindAllUsers_thenReturnUser() {
        Users user = new Users("stella", "Joel Stella", LocalDate.of(1995, 10, 28), new ArrayList<>());
        user.setPassword("123");
        Users user2 = new Users("stella2", "Joel Stella2", LocalDate.of(1995, 10, 30), new ArrayList<>());
        user2.setPassword("123");
        entityManager.persist(user);
        entityManager.persist(user2);

        Page<Users> users = userRepository.findAll(Pageable.unpaged());

        assertThat(users.getTotalPages()).isEqualTo(1);
    }
}
