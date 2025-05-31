package lt.viko.eif.madamkevicius.expansesapi.service;

import lt.viko.eif.madamkevicius.expansesapi.exception.PersonListIsEmptyException;
import lt.viko.eif.madamkevicius.expansesapi.exception.PersonNotFoundByIdException;
import lt.viko.eif.madamkevicius.expansesapi.exception.PersonNotFoundByUsernameException;
import lt.viko.eif.madamkevicius.expansesapi.model.entity.Person;
import lt.viko.eif.madamkevicius.expansesapi.repo.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public List<Person> getAll() {
        List<Person> personList = personRepo.findAll();

        if(personList.isEmpty()) {
            throw new PersonListIsEmptyException();
        }

        return personList;
    }

    public Person getById(int id) {
        return personRepo.findById(id).orElseThrow(() -> new PersonNotFoundByIdException(id));
    }

    public Person getByUsername(String username) {
        Person person =  personRepo.findByUsername(username);

        if(person == null) {
            throw new PersonNotFoundByUsernameException(username);
        }

        return person;
    }
}
