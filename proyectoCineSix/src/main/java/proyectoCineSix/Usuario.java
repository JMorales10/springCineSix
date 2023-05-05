package proyectoCineSix;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String correo;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nac")
	private Date fechaNac;

	private String nombre;

	private String password;

	private String rol;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="usuario")
	private List<Compra> compras;

	//bi-directional many-to-one association to Valoracione
	@OneToMany(mappedBy="usuario")
	private List<Valoracione> valoraciones;

	public Usuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setUsuario(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setUsuario(null);

		return compra;
	}

	public List<Valoracione> getValoraciones() {
		return this.valoraciones;
	}

	public void setValoraciones(List<Valoracione> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public Valoracione addValoracione(Valoracione valoracione) {
		getValoraciones().add(valoracione);
		valoracione.setUsuario(this);

		return valoracione;
	}

	public Valoracione removeValoracione(Valoracione valoracione) {
		getValoraciones().remove(valoracione);
		valoracione.setUsuario(null);

		return valoracione;
	}

}