package proyectoCineSix;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entrada database table.
 * 
 */
@Entity
@NamedQuery(name="Entrada.findAll", query="SELECT e FROM Entrada e")
public class Entrada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int asiento;

	private int fila;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="entrada")
	private List<Compra> compras;

	//bi-directional many-to-one association to Sala
	@ManyToOne
	@JoinColumn(name="id_sala")
	private Sala sala;

	//bi-directional many-to-one association to Pelicula
	@ManyToOne
	@JoinColumn(name="id_pelicula")
	private Pelicula pelicula;

	public Entrada() {
	}
	
	public Entrada(int id, Pelicula pelicula, Sala sala, int fila, int asiento) {
		this.id = id;
		this.pelicula = pelicula;
		this.sala = sala;
		this.fila = fila;
		this.asiento = asiento;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAsiento() {
		return this.asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	public int getFila() {
		return this.fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setEntrada(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setEntrada(null);

		return compra;
	}

	public Sala getSala() {
		return this.sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Pelicula getPelicula() {
		return this.pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

}