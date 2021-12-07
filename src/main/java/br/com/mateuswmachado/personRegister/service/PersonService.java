package br.com.mateuswmachado.personRegister.service;

import br.com.mateuswmachado.personRegister.dto.PersonDTO;
import br.com.mateuswmachado.personRegister.exception.PersonNotFoundException;
import br.com.mateuswmachado.personRegister.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PersonService {

    Page<PersonDTO> findAllPerson(Pageable pageable);

    ResponseEntity<PersonDTO> findPersonById(Long id) throws PersonNotFoundException;

    ResponseEntity<String> savePerson(Person person);

    ResponseEntity deletePerson(Long id) throws PersonNotFoundException;

    ResponseEntity<PersonDTO> updatePerson(Long id, Person person) throws PersonNotFoundException;
}
