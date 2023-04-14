package com.example.demo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pelicula database table.
 * 
 */
@Entity
@NamedQuery(name="Pelicula.findAll", query="SELECT p FROM Pelicula p")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripcion;

	private int duracion;

	private String genero;

	private String nombre;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="pelicula")
	private List<Compra> compras;

	//bi-directional many-to-one association to Entrada
	@OneToMany(mappedBy="pelicula")
	private List<Entrada> entradas;

	//bi-directional many-to-one association to Sala
	@ManyToOne
	@JoinColumn(name="id_sala")
	private Sala sala;

	//bi-directional many-to-one association to Valoracione
	@OneToMany(mappedBy="pelicula")
	private List<Valoracione> valoraciones;

	public Pelicula() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setPelicula(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setPelicula(null);

		return compra;
	}

	public List<Entrada> getEntradas() {
		return this.entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	public Entrada addEntrada(Entrada entrada) {
		getEntradas().add(entrada);
		entrada.setPelicula(this);

		return entrada;
	}

	public Entrada removeEntrada(Entrada entrada) {
		getEntradas().remove(entrada);
		entrada.setPelicula(null);

		return entrada;
	}

	public Sala getSala() {
		return this.sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Valoracione> getValoraciones() {
		return this.valoraciones;
	}

	public void setValoraciones(List<Valoracione> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public Valoracione addValoracione(Valoracione valoracione) {
		getValoraciones().add(valoracione);
		valoracione.setPelicula(this);

		return valoracione;
	}

	public Valoracione removeValoracione(Valoracione valoracione) {
		getValoraciones().remove(valoracione);
		valoracione.setPelicula(null);

		return valoracione;
	}

}