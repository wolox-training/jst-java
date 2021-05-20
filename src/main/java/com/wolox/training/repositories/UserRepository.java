package com.wolox.training.repositories;

import com.wolox.training.models.Users;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    public Page<Users> findAll(Pageable pageable);

    public Optional<Users> findByUserName(String userName);

    @Query("SELECT u FROM Users u WHERE (:infixName IS NULL OR lower(u.name) like lower(concat('%', :infixName,'%')))"
            + " and (cast(:startDate as timestamp) IS NULL OR cast(:endDate as timestamp) IS NULL OR u.birthDate BETWEEN :startDate AND :endDate)")
    public List<Users> findByNameIgnoreCaseContainingAndBirthDateBetween(
            @Param("infixName") String infix,
            @Param("startDate") LocalDate startBirthdate, @Param("endDate") LocalDate endBirthdate);

}
