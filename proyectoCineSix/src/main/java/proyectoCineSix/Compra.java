package proyectoCineSix;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the compra database table.
 * 
 */
@Entity
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_compra")
	private Date fechaCompra;

	private float precio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Entrada
	@ManyToOne
	@JoinColumn(name="id_entrada")
	private Entrada entrada;

	//bi-directional many-to-one association to Pelicula
	@ManyToOne
	@JoinColumn(name="id_pelicula")
	private Pelicula pelicula;

	public Compra() {
	}
	
	public Compra(int id, Usuario usuario, Entrada entrada, Pelicula pelicula, Date fecha_compra, float precio) {
		this.id = id;
		this.usuario = usuario;
		this.entrada = entrada;
		this.pelicula = pelicula;
		this.fechaCompra = fecha_compra;
		this.precio = precio;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Entrada getEntrada() {
		return this.entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Pelicula getPelicula() {
		return this.pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

}