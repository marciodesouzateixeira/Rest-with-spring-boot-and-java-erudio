package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	//private final AtomicLong counter = new AtomicLong();
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;

	public List<Person> findAll() {
		logger.info("Finding all people");
		
		//removendo os mocks
		/*List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;*/
		
		return repository.findAll();
	}
	
	public Person findById(Long id) {
		
		logger.info("Finding person id = " + id);
		
		//removendo mocks
		/*Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Marcio");
		person.setLastName("Teixeira");
		person.setAdress("Franca - SP");
		person.setGender("Male");
		return person;*/
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id") );
		
	}
	
	public Person create(Person person) {
		logger.info("Creating one person!");
		//return person;
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));		
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());

		//return person;
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one erson!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this Id"));
		
		repository.delete(entity);
	}
	
	/*
	 * removido por que criou BD
	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAdress("some address in Brazil " + 1);
		person.setGender("Male");
		return person;
	}
	*/
}
