package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "autori")
public class Autore {
	@Id
	private Integer idautore; // PK
	private String nome;
	private String cognome;

	// Relazione con Libro
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "autori")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JsonBackReference
	private Set<Libro> libri = new HashSet<>();

	// Costruttori
	public Autore() {
	}

	public Autore(int idautore, String nome, String cognome) {
		this.idautore = idautore;
		this.nome = nome;
		this.cognome = cognome;
	}

	public int getIdautore() {
		return idautore;
	}

	public void setIdautore(int idautore) {
		this.idautore = idautore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Set<Libro> getLibri() {
		return libri;
	}

	public void setLibri(Set<Libro> libri) {
		this.libri = libri;
	}

}
