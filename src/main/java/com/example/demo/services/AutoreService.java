package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Autore;


public interface AutoreService {
	 Autore getAutoreByidautore(int idautore);
	 public List<Autore> getAllAutori();
	 public void updateAutore(Autore autore);
	 public void deleteAutore(Autore autore);
	 public void insertAutore(Autore autore);
}
