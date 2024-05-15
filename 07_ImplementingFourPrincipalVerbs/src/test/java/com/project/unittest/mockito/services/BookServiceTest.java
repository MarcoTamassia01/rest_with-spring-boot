package com.project.unittest.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.data.vo.v1.BookVO;
import com.project.exceptions.RequiredObjectIsNullException;
import com.project.model.Book;
import com.project.repositories.BookRepository;
import com.project.services.BookService;
import com.project.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	
	MockBook input;
	
	@InjectMocks
	private BookService service;
	
	@Mock
	BookRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	
	@Test
	void testFindById() throws Exception {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertNotNull(result.getLaunch_date());
		assertTrue(result.toString().contains("links: [</book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertEquals("Tittle test1", result.getTitle());	
		assertEquals(25D, result.getPrice());
	}
		
	

	@Test
	void testFindyAll() {
		
		List<Book> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);
		
		var book = service.findyAll();
		
		assertNotNull(book);
		assertEquals(14, book.size());
		
		
		var bookOne = book.get(1);
		
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertNotNull(bookOne.getLaunch_date());
		
		assertTrue(bookOne.toString().contains("links: [</book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", bookOne.getAuthor());
		assertEquals("Tittle test1", bookOne.getTitle());	
		assertEquals(25D, bookOne.getPrice());
		
		var bookFour = book.get(4);
		
		assertNotNull(bookFour);
		assertNotNull(bookFour.getKey());
		assertNotNull(bookFour.getLinks());
		assertNotNull(bookFour.getLaunch_date());
		
		assertTrue(bookFour.toString().contains("links: [</book/v1/4>;rel=\"self\"]"));
		assertEquals("Author Test4", bookFour.getAuthor());
		assertEquals("Tittle test4", bookFour.getTitle());
		assertEquals(25D, bookFour.getPrice());
		
	
		var bookSeven = book.get(7);
		
		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven.getLinks());
		assertNotNull(bookSeven.getLaunch_date());
		
		assertTrue(bookSeven.toString().contains("links: [</book/v1/7>;rel=\"self\"]"));
		assertEquals("Author Test7", bookSeven.getAuthor());
		assertEquals("Tittle test7", bookSeven.getTitle());
		assertEquals(25D, bookSeven.getPrice());
		
	}
	
	
	@Test
	void testCreate() throws Exception {
		Book entity = input.mockEntity(1);
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertNotNull(result.getLaunch_date());
		
		assertTrue(result.toString().contains("links: [</book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertEquals("Tittle test1", result.getTitle());
		assertEquals(25D, result.getPrice());
	}
	
	@Test
	void testCreateWithNullBook() throws Exception {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "Its is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	
	}
	
	
	@Test
	void testUpdate() throws Exception {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertNotNull(result.getLaunch_date());
		
		assertTrue(result.toString().contains("links: [</book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertEquals("Tittle test1", result.getTitle());
		assertEquals(25D, result.getPrice());
	}
	
	@Test
	void testUpdateWithNullBook() throws Exception {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "Its is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

}
