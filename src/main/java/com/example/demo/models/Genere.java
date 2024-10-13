package com.example.demo.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="generi") // Tabella al plurale
public class Genere { // classe al singolare
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idgenere; // PK
	private String nomegenere;
	private String descrizione;
	// Relazione con Libro
	// Per eliminare -> (cascade = CascadeType.REMOVE)
	@OneToMany( fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name="idgenere")
	@JsonBackReference
	private Set<Libro> libri;
	public Genere() {}
	public Genere( String nomegenere) {
		this.nomegenere = nomegenere;
	}
}
