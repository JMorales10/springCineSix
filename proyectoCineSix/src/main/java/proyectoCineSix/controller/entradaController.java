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

import proyectoCineSix.Entrada;
import proyectoCineSix.repositorio.entradaRepositorio;
import proyectoCineSix.repositorio.peliculaRepositorio;
import proyectoCineSix.repositorio.salaRepositorio;

@CrossOrigin
@RestController
@RequestMapping("/entrada")
public class entradaController {
	
	@Autowired
	entradaRepositorio entRep;

	@Autowired
	peliculaRepositorio peliRep;
	
	@Autowired
	salaRepositorio salaRep;
	
	@GetMapping()
	public List<DTO> getEntradas() {
		List<DTO> listaEntradasDTO = new ArrayList<DTO>();

		List<Entrada> Entradas = entRep.findAll();

		for (Entrada p : Entradas) {
			DTO dtoEntrada = new DTO();

			dtoEntrada.put("id", p.getId());
			dtoEntrada.put("id_pelicula", p.getPelicula().getId());
			dtoEntrada.put("id_sala", p.getSala().getId());
			dtoEntrada.put("fila", p.getFila());
			dtoEntrada.put("asiento", p.getAsiento());
			dtoEntrada.put("fecha", p.getFecha());
			
			listaEntradasDTO.add(dtoEntrada);
		}

		return listaEntradasDTO;
	}
	
	@GetMapping("/{id}")
	public DTO getEntrada(@PathVariable("id") int id) {
		Entrada p = entRep.findById(id);

		DTO dtoEntrada = new DTO();

		if(p != null) {
			dtoEntrada.put("id", p.getId());
			dtoEntrada.put("id_pelicula", p.getPelicula());
			dtoEntrada.put("id_sala", p.getSala());
			dtoEntrada.put("fila", p.getFila());
			dtoEntrada.put("asiento", p.getAsiento());
			dtoEntrada.put("fecha", p.getFecha());
		}else {
			dtoEntrada.put("error", "not found");
		}

		return dtoEntrada;
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void crearEntrada(@RequestBody DatosAltaEntrada e, HttpServletRequest request) {
		entRep.save(new Entrada(e.id, peliRep.findById(e.id_pelicula), salaRep.findById(e.id_sala), e.fila, e.asiento, e.fecha));
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateEntrada(@RequestBody DatosAltaEntrada e, HttpServletRequest request, @PathVariable("id") int id) {
		entRep.save(new Entrada(id, peliRep.findById(e.id_pelicula), salaRep.findById(e.id_sala), e.fila, e.asiento, e.fecha));
	}
	
	static class DatosAltaEntrada {
		int id;
		int id_pelicula;
		int id_sala; 
		int fila;
		int asiento;
		Date fecha;

		public DatosAltaEntrada(int id, int id_pelicula, int id_sala, int fila, int asiento, Date fecha) {
			super();
			this.id = id;
			this.id_pelicula = id_pelicula;
			this.id_sala = id_sala;
			this.fila = fila;
			this.asiento = asiento;
			this.fecha = fecha;
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteEntrada(HttpServletRequest request, @PathVariable("id") int id){
		if(entRep.findById(id) != null) {
			entRep.deleteById(id);
		}
	}

}