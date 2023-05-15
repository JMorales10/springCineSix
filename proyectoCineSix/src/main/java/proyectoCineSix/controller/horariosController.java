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

import proyectoCineSix.Horario;
import proyectoCineSix.repositorio.horarioRepositorio;
import proyectoCineSix.repositorio.salaRepositorio;
import proyectoCineSix.repositorio.peliculaRepositorio;

@CrossOrigin
@RestController
@RequestMapping("/horarios")
public class horariosController {

	@Autowired
	horarioRepositorio horarioRep;
	
	@Autowired
	salaRepositorio salaRep;
	
	@Autowired
	peliculaRepositorio peliRep;
	
	@GetMapping()
	public List<DTO> getHorarios() {
		List<DTO> listaHorariosDTO = new ArrayList<DTO>();

		List<Horario> Horarios = horarioRep.findAll();

		for (Horario h : Horarios) {
			DTO dtoHorario = new DTO();

			dtoHorario.put("id", h.getId());
			dtoHorario.put("id_sala", h.getSala().getId());
			dtoHorario.put("id_pelicula", h.getPelicula().getId());
			dtoHorario.put("horario", h.getHorario());
			
			
			listaHorariosDTO.add(dtoHorario);
		}

		return listaHorariosDTO;
	}
	
	@GetMapping("/{id}")
	public DTO getHorario(@PathVariable("id") int id) {
		Horario h = horarioRep.findById(id);

		DTO dtoHorario = new DTO();

		if(h != null) {
			dtoHorario.put("id", h.getId());
			dtoHorario.put("id_sala", h.getSala().getId());
			dtoHorario.put("id_pelicula", h.getPelicula().getId());
			dtoHorario.put("horario", h.getHorario());
		}else {
			dtoHorario.put("error", "not found");
		}

		return dtoHorario;
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void crearHorario(@RequestBody DatosAltaHorario h, HttpServletRequest request) {
		horarioRep.save(new Horario(h.id, peliRep.findById(h.id_pelicula), salaRep.findById(h.id_sala), h.horario));
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateHorario(@RequestBody DatosAltaHorario h, HttpServletRequest request, @PathVariable("id") int id) {
		horarioRep.save(new Horario(h.id, peliRep.findById(h.id_pelicula), salaRep.findById(h.id_sala), h.horario));
	}
	
	static class DatosAltaHorario {
		int id;
		int id_sala;
		int id_pelicula;
		Date horario;

		public DatosAltaHorario(int id, int id_sala, int id_pelicula, Date horario) {
			super();
			this.id = id;
			this.id_sala = id_sala;
			this.id_pelicula = id_pelicula;
			this.horario = horario;
			
		}
	}
	
	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteHorario(HttpServletRequest request, @PathVariable("id") int id){
		if(horarioRep.findById(id) != null) {
			horarioRep.deleteById(id);
		}
	}
}
