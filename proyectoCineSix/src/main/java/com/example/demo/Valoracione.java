package com.example.demo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the valoraciones database table.
 * 
 */
@Entity
@Table(name="valoraciones")
@NamedQuery(name="Valoracione.findAll", query="SELECT v FROM Valoracione v")
public class Valoracione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String detalles;

	@Column(name="numero_estrellas")
	private int numeroEstrellas;

	private int titulo;

	//bi-directional many-to-one association to Pelicula
	@ManyToOne
	@JoinColumn(name="id_pelicula")
	private Pelicula pelicula;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Valoracione() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetalles() {
		return this.detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public int getNumeroEstrellas() {
		return this.numeroEstrellas;
	}

	public void setNumeroEstrellas(int numeroEstrellas) {
		this.numeroEstrellas = numeroEstrellas;
	}

	public int getTitulo() {
		return this.titulo;
	}

	public void setTitulo(int titulo) {
		this.titulo = titulo;
	}

	public Pelicula getPelicula() {
		return this.pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}