package com.peopleapi.util.creator;

import com.peopleapi.dto.PersonDTO;
import com.peopleapi.dto.PersonResquestDTO;
import com.peopleapi.entity.Person;
import com.peopleapi.mapper.PersonMapper;
import org.mapstruct.factory.Mappers;

import static com.peopleapi.util.PodamFactory.podam;

public class PersonCreator {

    public final static String VALID_CPF = "679.530.080-33";
    public final static String INVALID_CPF = "679.530.080-37";

    public static final Person person = podam.manufacturePojo(Person.class);
    public static final PersonDTO personDTO = Mappers.getMapper(PersonMapper.class).buildPersonDTO(person);

    public static final PersonResquestDTO createPersonRequestDTO() {
        return PersonResquestDTO.builder()
                .cpf(person.getCpf())
                .name(person.getName())
                .birthday(person.getBirthday())
                .contactList(person.getContactList())
                .build();
    }

    public static final PersonDTO updatePersonDTO() {
        return PersonDTO.builder()
                .id(person.getId())
                .cpf(person.getCpf())
                .name(person.getName())
                .birthday(person.getBirthday())
                .contactList(person.getContactList())
                .build();
    }
}
