package com.example.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.models.*;
public interface LibroRepository extends CrudRepository<Libro, Long>, PagingAndSortingRepository<Libro, Long> {
	Libro getLibroByidlibro(int idlibro);
	Page<Libro> findAll(Pageable pageable);
}
