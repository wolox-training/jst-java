package com.wolox.training.repositories;

import com.wolox.training.models.Users;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserName(String userName);

    public List<Users> findByNameIgnoreCaseContainingAndBirthDateBetween(String name, LocalDate startBirthdate,
            LocalDate endBirthdate);

}
