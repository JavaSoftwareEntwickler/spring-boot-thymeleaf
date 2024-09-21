package com.example.demo.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="generi") // Tabella al plurale
public class Genere { // classe al singolare
	@Id
	private int idgenere; // PK
	private String nomegenere;
	private String descrizione;
	
	// Relazione con Libro
	// Per eliminare -> (cascade = CascadeType.REMOVE)
	@OneToMany( fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name="idgenere")
	@JsonBackReference
	private Set<Libro> libri;
	
	// Costruttori
	public Genere() {}
	public Genere( String nomegenere) {
		this.nomegenere = nomegenere;
	}

	// Getter e setter tutti pubblici


	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getIdgenere() {
		return idgenere;
	}
	public void setIdgenere(int idgenere) {
		this.idgenere = idgenere;
	}
	public String getNomegenere() {
		return nomegenere;
	}
	public void setNomegenere(String nomegenere) {
		this.nomegenere = nomegenere;
	}

	public Set<Libro> getLibri() {
		return libri;
	}

	public void setLibri(Set<Libro> libri) {
		this.libri = libri;
	}
}
