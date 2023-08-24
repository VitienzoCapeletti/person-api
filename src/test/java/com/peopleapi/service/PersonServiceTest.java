package com.peopleapi.service;

import com.peopleapi.dto.PersonDTO;
import com.peopleapi.entity.Person;
import com.peopleapi.respository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.peopleapi.util.creator.PersonCreator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService service;
    @Mock
    private PersonRepository repository;

    @Test
    void createPersonWhenSuccessful() {
        when(repository.save(any())).thenReturn(person);
        assertEquals(personDTO, service.create(createPersonRequestDTO()));
    }

    @Test
    void createReturns_BAD_REQUEST_When_CPF_ALREADY_USED() {
        when(repository.save(any())).thenReturn(person);
        when(repository.findAllByCpf(any())).thenReturn(List.of(person));
        assertEquals(BAD_REQUEST, assertThrows(ResponseStatusException.class, () ->
                service.create(createPersonRequestDTO())).getStatusCode());
    }

    @Test
    void updatePersonDTOWhenSuccessful() {
        when(repository.findById(any())).thenReturn(Optional.of(person));
        when(repository.save(any())).thenReturn(person);
        assertEquals(personDTO, service.update(personDTO));
    }

    @Test
    void findByIdWhenSuccessful() {
        when(repository.findById(person.getId())).thenReturn(Optional.of(person));
        assertEquals(personDTO, service.findById(person.getId()));
    }

    @Test
    void findAllByIdWhenSuccessful() {
        final var listDTO = List.of(PersonDTO.builder().name("name1").build(), PersonDTO.builder().name("name2").build());
        final var listEntity = List.of(Person.builder().name("name1").build(), Person.builder().name("name2").build());
        Mockito.when(repository.findAll()).thenReturn(listEntity);
        var response = service.findAll();
        assertEquals(listDTO, response);
    }

    @Test
    void deleteWhenSuccessful() {
        final var entity = Person.builder().name("name1").id(1L).build();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        service.delete(1L);
    }
}