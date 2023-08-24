package com.peopleapi.respository;

import com.peopleapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByCpf(String cpf);
}
