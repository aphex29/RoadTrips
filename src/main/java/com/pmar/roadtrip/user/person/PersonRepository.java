package com.pmar.roadtrip.user.person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
    Optional<Person> findByAccountId(Long accountId);
}
