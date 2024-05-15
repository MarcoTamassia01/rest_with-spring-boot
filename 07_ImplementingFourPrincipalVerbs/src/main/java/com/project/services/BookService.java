package com.project.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.controllers.BookController;
import com.project.data.vo.v1.BookVO;
import com.project.exceptions.RequiredObjectIsNullException;
import com.project.exceptions.ResourceNotFound;
import com.project.mapper.DozerMapper;
import com.project.mapper.custom.BookMapper;
import com.project.model.Book;
import com.project.repositories.BookRepository;


@Service
public class BookService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());	
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	BookMapper mapper;
	
	public List<BookVO> findyAll(){
	var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);	
	books
	.stream()
	.forEach(p ->{
		try {
			p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});
	return books;
	}
	
	public BookVO findById(Long id) throws Exception{
		
		logger.info("Finding one book");
		
		var entity  = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}
	
		public BookVO create(BookVO book) throws Exception{
		
		if(book==null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one book register");
		var entity  = DozerMapper.parseObject(book, Book.class);
	
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;	
	}
	
		
		public BookVO update(BookVO book) throws Exception {
			
			if(book==null) throw new RequiredObjectIsNullException();
			logger.info("Update one book");
			
			var entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));
			
			entity.setAuthor(book.getAuthor());
			entity.setLaunch_date(book.getLaunch_date());
			entity.setPrice(book.getPrice());
			entity.setTitle(book.getTitle());
			
			var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
			vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
			return vo;	
		}
		
		public void delete(Long id) {
			logger.info("Delete one book suceffuly");
			
			var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Not records found for this ID!"));

			repository.delete(entity);
		}	
	
}
