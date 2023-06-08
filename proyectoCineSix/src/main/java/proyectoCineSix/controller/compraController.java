package proyectoCineSix.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyectoCineSix.Compra;
import proyectoCineSix.repositorio.compraRepositorio;
import proyectoCineSix.repositorio.peliculaRepositorio;
import proyectoCineSix.repositorio.salaRepositorio;
import proyectoCineSix.repositorio.usuarioRepositorio;

@CrossOrigin
@RestController
@RequestMapping("/compra")
public class compraController {

	@Autowired
	compraRepositorio compRep;
	
	@Autowired
	salaRepositorio salaRep;
	
	@Autowired
	usuarioRepositorio usuRep;
	
	@Autowired
	peliculaRepositorio peliRep;
	
	@GetMapping()
	public List<DTO> getCompras() {
		List<DTO> listaComprasDTO = new ArrayList<DTO>();

		List<Compra> compras = compRep.findAll();

		for (Compra c : compras) {
			DTO dtoCompra = new DTO();

			dtoCompra.put("id", c.getId());
			dtoCompra.put("id_usuario", c.getUsuario().getId());
			dtoCompra.put("id_sala", c.getSala().getId());
			dtoCompra.put("id_pelicula", c.getPelicula().getId());
			dtoCompra.put("fecha", c.getFecha());
			dtoCompra.put("precio", c.getPrecio());
			dtoCompra.put("fila", c.getFila());
			dtoCompra.put("asiento", c.getAsiento());
			
			
			listaComprasDTO.add(dtoCompra);
		}

		return listaComprasDTO;
	}
	
	@GetMapping("/{id}")
	public DTO getCompra(@PathVariable("id") int id) {
		Compra c = compRep.findById(id);

		DTO dtoCompra = new DTO();

		if(c != null) {
			dtoCompra.put("id", c.getId());
			dtoCompra.put("id_usuario", c.getUsuario().getId());
			dtoCompra.put("id_sala", c.getSala().getId());
			dtoCompra.put("id_pelicula", c.getPelicula().getId());
			dtoCompra.put("fecha", c.getFecha());
			dtoCompra.put("precio", c.getPrecio());
			dtoCompra.put("fila", c.getFila());
			dtoCompra.put("asiento", c.getAsiento());
		}else {
			dtoCompra.put("error", "not found");
		}

		return dtoCompra;
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void crearCompra(@RequestBody DatosAltaCompra c, HttpServletRequest request) {
		compRep.save(new Compra(c.id, usuRep.findById(c.id_usuario), salaRep.findById(c.id_sala), peliRep.findById(c.id_pelicula), c.fecha_compra, c.precio, c.fila, c.asiento));
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCompra(@RequestBody DatosAltaCompra c, HttpServletRequest request, @PathVariable("id") int id) {
		compRep.save(new Compra(c.id, usuRep.findById(c.id_usuario), salaRep.findById(c.id_sala), peliRep.findById(c.id_pelicula), c.fecha_compra, c.precio, c.fila, c.asiento));
	}
	
	static class DatosAltaCompra {
		int id;
		int id_usuario;
		int id_sala;
		int id_pelicula;
		Date fecha_compra;
		float precio;
		int fila;
		int asiento;

		public DatosAltaCompra(int id, int id_usuario, int id_sala, int id_pelicula, Date fecha_compra, float precio, int fila, int asiento) {
			super();
			this.id = id;
			this.id_usuario = id_usuario;
			this.id_sala = id_sala;
			this.id_pelicula = id_pelicula;
			this.fecha_compra = fecha_compra;
			this.precio = precio;
			this.fila = fila;
			this.asiento = asiento;
			
		}
	}
	
	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCompra(HttpServletRequest request, @PathVariable("id") int id){
		if(compRep.findById(id) != null) {
			compRep.deleteById(id);
		}
	}
}