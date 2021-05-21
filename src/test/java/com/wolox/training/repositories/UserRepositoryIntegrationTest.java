package com.wolox.training.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.wolox.training.models.Users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

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
        entityManager.persist(user);

        Optional<Users> foundUser = userRepository.findByUserName("stella");

        assertThat(foundUser.get().getName()).isEqualTo("Joel Stella");
    }
}
