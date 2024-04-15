package com.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.project.model.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter =  new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	
	public List<Person> findyAll() {

		logger.info("Finding all people");
		List<Person>persons = new ArrayList<>();
		for(int i=0;i<8;i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}
	
	
	public Person findById(String id) {
		
		logger.info("Finding one person");
		
		Person person =  new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Marco");
		person.setLastName("Tamassia");
		person.setAddress("Bragança Paulista - São Paulo");
		person.setGender("Male");
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Creating one person");
	
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Update one person");
		
		return person;
	}
	
	public void delete(String id) {
		logger.info("Delete one person suceffuly");

	}
	
	
	private Person mockPerson(int i) {
		
		Person person =  new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " +i);
		person.setLastName("Last name "+i);
		person.setAddress("Some address in Brasil " +i);
		person.setGender("Male");
		return person;
	}
	
	
}
