package proyectoCineSix;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sala database table.
 * 
 */
@Entity
@NamedQuery(name="Sala.findAll", query="SELECT s FROM Sala s")
public class Sala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int capacidad;

	@Column(name="numero_sala")
	private int numeroSala;

	//bi-directional many-to-one association to Entrada
	@OneToMany(mappedBy="sala")
	private List<Entrada> entradas;

	//bi-directional many-to-one association to Pelicula
	@OneToMany(mappedBy="sala")
	private List<Pelicula> peliculas;

	public Sala() {
	}
	
	public Sala(int id, int numero_sala, int capacidad) {
		this.id = id;
		this.numeroSala = numero_sala;
		this.capacidad = capacidad;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getNumeroSala() {
		return this.numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public List<Entrada> getEntradas() {
		return this.entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	public Entrada addEntrada(Entrada entrada) {
		getEntradas().add(entrada);
		entrada.setSala(this);

		return entrada;
	}

	public Entrada removeEntrada(Entrada entrada) {
		getEntradas().remove(entrada);
		entrada.setSala(null);

		return entrada;
	}

	public List<Pelicula> getPeliculas() {
		return this.peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public Pelicula addPelicula(Pelicula pelicula) {
		getPeliculas().add(pelicula);
		pelicula.setSala(this);

		return pelicula;
	}

	public Pelicula removePelicula(Pelicula pelicula) {
		getPeliculas().remove(pelicula);
		pelicula.setSala(null);

		return pelicula;
	}

}