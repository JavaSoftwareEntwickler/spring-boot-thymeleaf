package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.*;

public interface GenereRepository extends CrudRepository<Genere, Long> {

	Genere getGenereByidgenere(int idgenere);

}
