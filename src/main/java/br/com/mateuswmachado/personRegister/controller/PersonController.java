package br.com.mateuswmachado.personRegister.controller;

import br.com.mateuswmachado.personRegister.dto.PersonDTO;
import br.com.mateuswmachado.personRegister.exception.PersonNotFoundException;
import br.com.mateuswmachado.personRegister.model.Person;
import br.com.mateuswmachado.personRegister.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    @Cacheable(value = "listPersons")
    public Page<PersonDTO> findAll(Pageable pageable) {
        return personService.findAllPerson(pageable);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findPersonById(id);
    }

    @PostMapping("/person")
    @CacheEvict(value = "listPersons", allEntries = true)
    public ResponseEntity<String> save(@Valid @RequestBody Person person) {
        return personService.savePerson(person);
    }

    @DeleteMapping("/person/{id}")
    @CacheEvict(value = "listPersons", allEntries = true)
    public ResponseEntity delete(@PathVariable Long id) throws PersonNotFoundException {
        return personService.deletePerson(id);
    }

    @PutMapping("/person/{id}")
    @CacheEvict(value = "listPersons", allEntries = true)
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody Person person) throws PersonNotFoundException{
        return personService.updatePerson(id, person);
    }

}
