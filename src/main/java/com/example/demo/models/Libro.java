package com.example.demo.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name="libri")
public class Libro { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idlibro; // PK
	@NotBlank(message="Campo richiesto")
	private String titolo;
	@Column(length = 500)
	private String descrizione;
	private Double prezzo;
	private String copertina;
	private Integer anno;

	@Column(name = "data_inserimento", nullable = true, unique = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInserimento;
	@Column(nullable = true)
	private Integer giudizio;
	
	@Basic(optional = true)
	@Column(name = "data", nullable = true, unique = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	private Integer idgenere; // FK
	// Relazione con Genere
	@ManyToOne()
	@JoinColumn(name="idgenere",referencedColumnName="idgenere", insertable=false, updatable=false)
	private Genere genere;
	// Relazioni con Autore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "autorilibri", 
        joinColumns = { @JoinColumn(name = "idlibro") }, 
        inverseJoinColumns = { @JoinColumn(name = "idautore") }
    )
    @Cascade(value = CascadeType.SAVE_UPDATE)
    @JsonBackReference
	private Set<Autore> autori = new HashSet<>();
    
    public void addAutore(Autore a) {
        autori.add(a);
        a.getLibri().add(this);
    }

    public void removeAutore(Autore b) {
        autori.remove(b);
        b.getLibri().remove(this);
    }

	@Override
	public int hashCode() {
		return Objects.hash(idlibro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return idlibro == other.idlibro;
	}
}
