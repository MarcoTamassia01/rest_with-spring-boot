package com.project.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.controllers.PersonController;
import com.project.data.vo.v1.PersonVO;
import com.project.data.vo.v2.PersonVOV2;
import com.project.exceptions.RequiredObjectIsNullException;
import com.project.exceptions.ResourceNotFound;
import com.project.mapper.DozerMapper;
import com.project.mapper.custom.PersonMapper;
import com.project.model.Person;
import com.project.repositories.PersonRepository;

@Service
public class PersonService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	
	public List<PersonVO> findyAll() {
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons
			.stream()
			.forEach(p ->{
				try {
					p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		return persons;
	}
	
	
	public PersonVO findById(Long id) throws Exception{
		
		logger.info("Finding one person");
		
		var entity  = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
		
	}
	
	public PersonVO create(PersonVO person) throws Exception{
		
		if(person==null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person");
		var entity  = DozerMapper.parseObject(person, Person.class);
	
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;	
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		
		logger.info("Creating one person WITH V2");
		var entity  = mapper.convertVoToEntity(person);
		
		var vo = mapper.convertEntityToVo(repository.save(entity));
		return vo;	
	}
	
		
	public PersonVO update(PersonVO person) throws Exception {
		
		if(person==null) throw new RequiredObjectIsNullException();
		logger.info("Update one person");
		
		var entity = repository.findById(person.getKey())
		.orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;	
	}
	
	public void delete(Long id) {
		logger.info("Delete one person suceffuly");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));

		repository.delete(entity);
	}	
}
