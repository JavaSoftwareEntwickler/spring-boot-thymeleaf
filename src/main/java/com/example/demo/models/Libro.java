package com.example.demo.models;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
// JPA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank; // Validazione
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Libri") 
public class Libro { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idlibro; // PK
	@NotBlank(message="Campo richiesto")
	private String titolo;
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
    // Costruttori
	public Libro() {
	}
	public Libro(String titolo, String descrizione, double prezzo, String copertina, int anno, LocalDate dataInserimento,int giudizio,LocalDate data, Integer idgenere) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo= prezzo;
		this.copertina = copertina;
		this.anno = anno;
		this.dataInserimento = dataInserimento;
		this.giudizio = giudizio;
		this.data=data;
		this.idgenere = idgenere;
	}
	
	// Getter e setter

	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public int getIdlibro() {
		return idlibro;
	}
	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	public String getCopertina() {
		return copertina;
	}
	public void setCopertina(String copertina) {
		this.copertina = copertina;
	}
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public Integer getGiudizio() {
		return giudizio;
	}
	public void setGiudizio(Integer giudizio) {
		this.giudizio = giudizio;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		
		this.data = data; 
	}
	public int getIdgenere() {
		return idgenere;
	}
	public void setIdgenere(Integer idgenere) {
		this.idgenere = idgenere;
	}
	public Genere getGenere() {
		return genere;
	}
	public void setGenere(Genere genere) {
		this.genere = genere;
	}
	public Set<Autore> getAutori() {
		return autori;
	}
	public void setAutori(Set<Autore> autori) {
		this.autori = autori;
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
