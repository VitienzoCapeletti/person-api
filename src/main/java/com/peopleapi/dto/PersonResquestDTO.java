package com.peopleapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peopleapi.entity.Contact;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonResquestDTO {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private String cpf;
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    @PastOrPresent
    private LocalDate birthday;
    @NotNull
    private List<Contact> contactList;

}
