package com.peopleapi.service;

import com.peopleapi.dto.PersonDTO;
import com.peopleapi.dto.PersonResquestDTO;
import com.peopleapi.entity.Person;
import com.peopleapi.mapper.PersonMapper;
import com.peopleapi.respository.PersonRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
        this.mapper = Mappers.getMapper(PersonMapper.class);
    }

    public PersonDTO create(final PersonResquestDTO requestDTO) {

        if (!repository.findAllByCpf(requestDTO.getCpf()).isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Error: CPF already used");
        }

        final var person = mapper.buildPersonEntity(requestDTO);
        return savePerson(person);
    }

    public PersonDTO update(PersonDTO personDTO) {
        findEntityById(personDTO.getId());
        final var person = mapper.buildPersonEntity(personDTO);
        return savePerson(person);
    }

    public List<PersonDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::buildPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(final Long personId) {
        return repository.findById(personId)
                .map(mapper::buildPersonDTO)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Person not found"));
    }

    public void delete(final Long personId) {
        final var person = findEntityById(personId);
        repository.delete(person);
    }


    private Person findEntityById(final Long personId) {
        return repository.findById(personId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Person not found"));
    }

    private PersonDTO savePerson(Person person) {
        final var savedPerson = repository.save(person);
        return mapper.buildPersonDTO(savedPerson);
    }
}
