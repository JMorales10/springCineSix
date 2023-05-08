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

import proyectoCineSix.Sala;
import proyectoCineSix.repositorio.salaRepositorio;

@CrossOrigin
@RestController
@RequestMapping("/sala")
public class salaController {
	
	@Autowired
	salaRepositorio salaRep;
	
	@GetMapping()
	public List<DTO> getSalas() {
		List<DTO> listaSalasDTO = new ArrayList<DTO>();

		List<Sala> Salas = salaRep.findAll();

		for (Sala p : Salas) {
			DTO dtoSala = new DTO();

			dtoSala.put("id", p.getId());
			dtoSala.put("numero_sala", p.getNumeroSala());
			dtoSala.put("capacidad", p.getCapacidad());
			
			listaSalasDTO.add(dtoSala);
		}

		return listaSalasDTO;
	}
	
	@GetMapping("/{id}")
	public DTO getSala(@PathVariable("id") int id) {
		Sala p = salaRep.findById(id);

		DTO dtoSala = new DTO();

		if(p != null) {
			dtoSala.put("id", p.getId());
			dtoSala.put("numero_sala", p.getNumeroSala());
			dtoSala.put("capacidad", p.getCapacidad());
		}else {
			dtoSala.put("error", "not found");
		}

		return dtoSala;
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void crearSala(@RequestBody DatosAltaSala s, HttpServletRequest request) {
		salaRep.save(new Sala(s.id, s.numero_sala, s.capacidad));
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateSala(@RequestBody DatosAltaSala s, HttpServletRequest request, @PathVariable("id") int id) {
		salaRep.save(new Sala(id, s.numero_sala, s.capacidad));
	}
	
	static class DatosAltaSala {
		int id;
		int numero_sala;
		int capacidad;

		public DatosAltaSala(int id, int numero_sala, int capacidad) {
			super();
			this.id = id;
			this.numero_sala = numero_sala;
			this.capacidad = capacidad;
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteSala(HttpServletRequest request, @PathVariable("id") int id){
		if(salaRep.findById(id) != null) {
			salaRep.deleteById(id);
		}
	}

}