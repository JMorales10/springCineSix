package proyectoCineSix.controller;

import java.util.ArrayList;
import java.util.Date;
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

import proyectoCineSix.Usuario;
import proyectoCineSix.repositorio.usuarioRepositorio;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class usuarioController {
	
	@Autowired
	usuarioRepositorio usuRep;
	
	@GetMapping()
	public List<DTO> getUsuarios() {
		List<DTO> listaUsuariosDTO = new ArrayList<DTO>();

		List<Usuario> usuarios = usuRep.findAll();

		for (Usuario u : usuarios) {
			DTO dtoUsuaria = new DTO();

			dtoUsuaria.put("id", u.getId());
			dtoUsuaria.put("nombre", u.getNombre());
			dtoUsuaria.put("correo", u.getCorreo());
			dtoUsuaria.put("password", u.getPassword());
			dtoUsuaria.put("fecha_nac", u.getFechaNac());
			dtoUsuaria.put("rol", u.getRol());
			
			listaUsuariosDTO.add(dtoUsuaria);
		}

		return listaUsuariosDTO;
	}
	
	@GetMapping("/{id}")
	public DTO getUsuario(@PathVariable("id") int id) {
		Usuario u = usuRep.findById(id);

		DTO dtoUsuaria = new DTO();

		if(u != null) {
			dtoUsuaria.put("id", u.getId());
			dtoUsuaria.put("nombre", u.getNombre());
			dtoUsuaria.put("correo", u.getCorreo());
			dtoUsuaria.put("password", u.getPassword());
			dtoUsuaria.put("fecha_nac", u.getFechaNac());
			dtoUsuaria.put("rol", u.getRol());
		}else {
			dtoUsuaria.put("error", "not found");
		}

		return dtoUsuaria;
	}
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void crearUsuario(@RequestBody DatosAltaUsuario u, HttpServletRequest request) {
		usuRep.save(new Usuario(u.id, u.nombre, u.correo, u.password, u.fechaNac, u.rol));
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUsuario(@RequestBody DatosAltaUsuario u, HttpServletRequest request, @PathVariable("id") int id) {
		usuRep.save(new Usuario(id, u.nombre, u.correo, u.password, u.fechaNac, u.rol));
	}
	
	static class DatosAltaUsuario {
		int id;
		String nombre;
		String correo;
		String password;
		Date fechaNac;
		String rol;

		public DatosAltaUsuario(int id, String nombre, String correo, String password, Date fechaNac, String rol) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.correo = correo;
			this.password = password;
			this.fechaNac = fechaNac;
			this.rol = rol;
		}
	}
	
	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUsuario(HttpServletRequest request, @PathVariable("id") int id){
		if(usuRep.findById(id) != null) {
			usuRep.deleteById(id);
		}
	}
}
