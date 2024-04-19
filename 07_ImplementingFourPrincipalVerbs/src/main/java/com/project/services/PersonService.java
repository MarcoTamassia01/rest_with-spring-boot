package com.project.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.data.vo.v1.PersonVO;
import com.project.exceptions.ResourceNotFound;
import com.project.mapper.DozerMapper;
import com.project.model.Person;
import com.project.repositories.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	PersonRepository repository;
	
	
	public List<PersonVO> findyAll() {
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	
	public PersonVO findById(Long id) {
		
		logger.info("Finding one person");
		
		var entity  = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));
		return DozerMapper.parseObject(entity, PersonVO.class);
		
	}
	
	public PersonVO create(PersonVO person) {
		
		logger.info("Creating one person");
		var entity  = DozerMapper.parseObject(person, Person.class);
	
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;		
	}
	
		
	public PersonVO update(PersonVO person) {
		logger.info("Update one person");
		
		var entity = repository.findById(person.getId())
		.orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;		
	}
	
	public void delete(Long id) {
		logger.info("Delete one person suceffuly");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));

		repository.delete(entity);
	}	
}
