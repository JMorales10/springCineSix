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

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="sala")
	private List<Compra> compras;

	//bi-directional many-to-one association to Horario
	@OneToMany(mappedBy="sala")
	private List<Horario> horarios;

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

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setSala(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setSala(null);

		return compra;
	}

	public List<Horario> getHorarios() {
		return this.horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public Horario addHorario(Horario horario) {
		getHorarios().add(horario);
		horario.setSala(this);

		return horario;
	}

	public Horario removeHorario(Horario horario) {
		getHorarios().remove(horario);
		horario.setSala(null);

		return horario;
	}

}