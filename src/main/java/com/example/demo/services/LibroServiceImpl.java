package com.example.demo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.models.*;
import com.example.demo.repositories.LibroRepository;

@Service
@Transactional
public class LibroServiceImpl implements LibroService{
	@Autowired
    private LibroRepository libroRepository;
	
	@Override
	public List<Libro> getAllBooks() {
		 List<Libro> result = (List<Libro>) libroRepository.findAll();
		 return result;
	}
	
	@Override
	public Libro getLibroByidlibro(int idlibro) {
		Libro libro = libroRepository.getLibroByidlibro(idlibro);		
		return libro;
	}

	@Override
	public void updateLibro(Libro book) {
		libroRepository.save(book);
	}


	@Override
	public void insertLibro(Libro book) {
		libroRepository.save(book);
	}

	@Override
	public Page<Libro> getPaginatedArticles(PageRequest pageable) {
		return libroRepository.findAll(pageable);
	}

	@Override
	public void deleteLibro(Libro book) {
		libroRepository.delete(book);
	}
	
	@Override
	public void deleteLibroByidlibro(int idlibro) {
		Libro libro = libroRepository.getLibroByidlibro(idlibro);
		libroRepository.delete(libro);
	}


}
