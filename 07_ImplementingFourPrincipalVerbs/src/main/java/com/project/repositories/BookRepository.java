package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
}
