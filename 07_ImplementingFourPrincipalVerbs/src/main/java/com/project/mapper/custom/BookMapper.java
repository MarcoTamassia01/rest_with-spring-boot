package com.project.mapper.custom;

import org.springframework.stereotype.Service;

import com.project.data.vo.v1.BookVO;
import com.project.model.Book;

@Service
public class BookMapper {
	
	public BookVO convertEntityToVo (Book book) {
		BookVO vo = new BookVO();
		vo.setKey(book.getId());
		vo.setAuthor(book.getAuthor());
		vo.setLaunch_date(book.getLaunch_date());
		vo.setPrice(book.getPrice());
		vo.setTitle(book.getTitle());

		return vo;
	}
	
	
	public Book convertVoToEntity (BookVO book) {
		Book entity = new Book();
		entity.setId(book.getKey());
		entity.setAuthor(book.getAuthor());
		entity.setLaunch_date(book.getLaunch_date());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());

		return entity;
	}

}
