package com.peopleapi.dto;

import com.peopleapi.entity.Person;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String number;
    @NotNull
    private String email;
    @NotNull
    private Person person;

}
