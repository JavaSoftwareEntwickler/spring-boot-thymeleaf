package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "autori")
public class Autore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idautore; // PK
	private String nome;
	private String cognome;

	// Relazione con Libro
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "autori")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JsonBackReference
	private Set<Libro> libri = new HashSet<>();

	public Autore() {
	}

	public Autore(int idautore, String nome, String cognome) {
		this.idautore = idautore;
		this.nome = nome;
		this.cognome = cognome;
	}

}
