package br.com.mateuswmachado.personRegister.service;

import br.com.mateuswmachado.personRegister.dto.PersonDTO;
import br.com.mateuswmachado.personRegister.exception.PersonNotFoundException;
import br.com.mateuswmachado.personRegister.model.Person;
import br.com.mateuswmachado.personRegister.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImp implements PersonService{

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private PersonRepository personRepository;

    public Page<PersonDTO> findAllPerson(Pageable pageable) {
        Page<Person> listAll = personRepository.findAll(pageable);
        return new PageImpl<>(listAll.stream().map(person -> modelMapper.map(person, PersonDTO.class)).collect(Collectors.toList()));
    }

    public ResponseEntity<PersonDTO> findPersonById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        return ResponseEntity.ok().body(personDTO);
    }

    public ResponseEntity<String> savePerson(Person person) {
        personRepository.save(person);
        return ResponseEntity.ok().body("Pessoa com o Id " + person.getId() + " criada");
    }

    public ResponseEntity deletePerson(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        personRepository.delete(person);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<PersonDTO> updatePerson(Long id, Person person) {
        return personRepository.findById(id).map(newPerson -> {
            newPerson.setFirstName(person.getFirstName());
            newPerson.setLastName(person.getLastName());
            newPerson.setCpf(person.getCpf());
            newPerson.setAddress(person.getAddress());
            Person updatedPerson = personRepository.save(newPerson);
            return ResponseEntity.ok().body(modelMapper.map(updatedPerson, PersonDTO.class));
        }).orElse(ResponseEntity.notFound().build());
    }


    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }


}
