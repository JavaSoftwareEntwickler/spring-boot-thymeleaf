package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Genere;
import com.example.demo.repositories.GenereRepository;

@Service
@Transactional
public class GenereServiceImpl implements GenereService{
	@Autowired
    private GenereRepository genereRepository;
	
	@Override
	public List<Genere> getAllGeneri() {
		List<Genere> generi = (List<Genere>) genereRepository.findAll();      
		return generi;
	}
	@Override
	public Genere getGenereByidgenere(int idgenere) {
		Genere genere = genereRepository.getGenereByidgenere(idgenere);		
		return genere;
	}

	@Override
	public void updateGenere(Genere genere) {
		genereRepository.save(genere);
	}

	@Override
	public void deleteGenere(Genere genere) {
		genereRepository.delete(genere);
	}

	@Override
	public void insertGenere(Genere genere) {
		genereRepository.save(genere);
	}



}
