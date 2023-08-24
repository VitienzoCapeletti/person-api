package com.peopleapi.mapper;

import com.peopleapi.dto.PersonDTO;
import com.peopleapi.dto.PersonResquestDTO;
import com.peopleapi.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    Person buildPersonEntity(PersonResquestDTO person);

    Person buildPersonEntity(PersonDTO person);

    PersonDTO buildPersonDTO(Person person);

}
