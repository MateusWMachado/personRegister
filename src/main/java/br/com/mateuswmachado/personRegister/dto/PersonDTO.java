package br.com.mateuswmachado.personRegister.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private AddressDTO address;

}
