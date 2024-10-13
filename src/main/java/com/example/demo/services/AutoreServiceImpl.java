package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Autore;
import com.example.demo.repositories.AutoreRepository;

@Service
@Transactional
public class AutoreServiceImpl implements AutoreService{
	@Autowired
    private AutoreRepository autoreRepository;
	@Override
	public Autore getAutoreByidautore(int idautore) {
		Autore autore = autoreRepository.getAutoreByidautore(idautore);		
		return autore;
	}

	@Override
	public List<Autore> getAllAutori() {
		List<Autore> autori = (List<Autore>) autoreRepository.findAll();      
		return autori;
	}

	@Override
	public void updateAutore(Autore autore) {
		autoreRepository.save(autore);
	}

	@Override
	public void deleteAutore(Autore autore) {
		autoreRepository.delete(autore);
	}

	@Override
	public void insertAutore(Autore autore) {
		autoreRepository.save(autore);
	}

}
