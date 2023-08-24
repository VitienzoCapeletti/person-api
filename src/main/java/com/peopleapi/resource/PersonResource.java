package com.peopleapi.resource;

import com.peopleapi.dto.PersonDTO;
import com.peopleapi.dto.PersonResquestDTO;
import com.peopleapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/person")
@RestController
public class PersonResource {

    @Autowired
    private PersonService service;

    @PostMapping
    public PersonDTO create(@RequestBody PersonResquestDTO resquestDTO) {
        return service.create(resquestDTO);
    }

    @PutMapping
    public PersonDTO update(@RequestBody PersonDTO personDTO) {
        return service.update(personDTO);
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
