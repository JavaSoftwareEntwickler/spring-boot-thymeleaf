package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Genere;

public interface GenereService {
	 Genere getGenereByidgenere(int idgenere);
	 public List<Genere> getAllGeneri();
	 public void updateGenere(Genere genere);
	 public void deleteGenere(Genere genere);
	 public void insertGenere(Genere genere);
}
