package br.com.mateuswmachado.personRegister.repository;

import br.com.mateuswmachado.personRegister.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
