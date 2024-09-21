package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.models.*;

public interface LibroService {
	 public List<Libro> getAllBooks();
	 public Libro getLibroByidlibro(int idlibro);
	 public void updateLibro(Libro book);
	 public void deleteLibro(Libro book);
	 public void insertLibro(Libro book);
	 public Page<Libro> getPaginatedArticles(PageRequest pageable);
	 void deleteLibroByidlibro(int idlibro);
}
