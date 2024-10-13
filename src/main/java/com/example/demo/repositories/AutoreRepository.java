package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.*;

public interface AutoreRepository extends CrudRepository<Autore, Integer> {
	Autore getAutoreByidautore(Integer idautore);
}

