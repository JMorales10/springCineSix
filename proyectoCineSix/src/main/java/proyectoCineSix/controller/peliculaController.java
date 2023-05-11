package proyectoCineSix.controller;

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

import proyectoCineSix.Pelicula;
import proyectoCineSix.repositorio.peliculaRepositorio;
import proyectoCineSix.repositorio.salaRepositorio;

@CrossOrigin
@RestController
@RequestMapping("/pelicula")
public class peliculaController {

	@Autowired
	peliculaRepositorio peliRep;
	
	@Autowired
	salaRepositorio salaRep;
	
	@GetMapping()
	public List<DTO> getPeliculas() {
		List<DTO> listaPeliculasDTO = new ArrayList<DTO>();

		List<Pelicula> Peliculas = peliRep.findAll();

		for (Pelicula p : Peliculas) {
			DTO dtoPelicula = new DTO();

			dtoPelicula.put("id", p.getId());
			dtoPelicula.put("id_sala", p.getSala().getId());
			dtoPelicula.put("nombre", p.getNombre());
			dtoPelicula.put("genero", p.getGenero());
			dtoPelicula.put("duracion", p.getDuracion());
			dtoPelicula.put("descripcion", p.getDescripcion());
			dtoPelicula.put("imagen", p.getImagen());
			
			listaPeliculasDTO.add(dtoPelicula);
		}

		return listaPeliculasDTO;
	}
	
	@GetMapping("/{id}")
	public DTO getPelicula(@PathVariable("id") int id) {
		Pelicula p = peliRep.findById(id);

		DTO dtoPelicula = new DTO();

		if(p != null) {
			dtoPelicula.put("id", p.getId());
			dtoPelicula.put("id_sala", p.getSala().getId());
			dtoPelicula.put("nombre", p.getNombre());
			dtoPelicula.put("genero", p.getGenero());
			dtoPelicula.put("duracion", p.getDuracion());
			dtoPelicula.put("descripcion", p.getDescripcion());
			dtoPelicula.put("imagen", p.getImagen());
		}else {
			dtoPelicula.put("error", "not found");
		}

		return dtoPelicula;
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void crearPelicula(@RequestBody DatosAltaPelicula p, HttpServletRequest request) {
		peliRep.save(new Pelicula(p.id, salaRep.findById(p.id_sala), p.nombre, p.genero, p.duracion, p.descripcion, p.imagen));
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePelicula(@RequestBody DatosAltaPelicula p, HttpServletRequest request, @PathVariable("id") int id) {
		peliRep.save(new Pelicula(id, salaRep.findById(p.id_sala), p.nombre, p.genero, p.duracion, p.descripcion, p.imagen));
	}
	
	static class DatosAltaPelicula {
		int id;
		int id_sala; 
		String nombre;
		String genero;
		int duracion;
		String descripcion;
		String imagen;

		public DatosAltaPelicula(int id, int id_sala, String nombre, String genero, int duracion, String descripcion, String imagen) {
			super();
			this.id = id;
			this.id_sala = id_sala;
			this.nombre = nombre;
			this.genero = genero;
			this.duracion = duracion;
			this.descripcion = descripcion;
			this.imagen = imagen;
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public void deletePelicula(HttpServletRequest request, @PathVariable("id") int id){
		if(peliRep.findById(id) != null) {
			peliRep.deleteById(id);
		}
	}

}