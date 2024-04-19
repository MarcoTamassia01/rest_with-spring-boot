package com.project.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exceptions.ResourceNotFound;
import com.project.model.Person;
import com.project.repositories.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	PersonRepository repository;
	
	
	public List<Person> findyAll() {

		return repository.findAll();
	}
	
	
	public Person findById(Long id) {
		
		logger.info("Finding one person");
	
		return repository.findById(id).orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));
	}
	
	public Person create(Person person) {
		logger.info("Creating one person");
	
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Update one person");
		
		Person entity = repository.findById(person.getId())
		.orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		logger.info("Delete one person suceffuly");
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));

		
		
		repository.delete(entity);
	}	
}
