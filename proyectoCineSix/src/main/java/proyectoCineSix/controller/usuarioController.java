package proyectoCineSix.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import proyectoCineSix.jwtSecurity.AutenticadorJWT;
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
	
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public DTO autentitcaUsuario(@RequestBody DatosAutenticacionUsuario datos, HttpServletRequest request,
			HttpServletResponse response) {

		DTO dto = new DTO();
		dto.put("result", "fail");

		Usuario usuAutenticado = usuRep.findByCorreoAndPassword(datos.correo, datos.password);

		// si existe el usuario y los datos son correctos, devolveremos un success y
		// todos los datos del usuario
		if (usuAutenticado != null) {
			dto.put("result", "success");

			// devolvemos jwt que usaremos de ahora en adelante en las cabeceras para
			// identificar al usuario
			dto.put("jwt", AutenticadorJWT.codificaJWT(usuAutenticado));

			// Prueba cookie. Quita el request y el response
			Cookie cook = new Cookie("jwt", AutenticadorJWT.codificaJWT(usuAutenticado));
			cook.setMaxAge(-1);
			response.addCookie(cook);
			//
		}

		return dto;
	}
		
	static class DatosAutenticacionUsuario {
		String correo;
		String password;

		public DatosAutenticacionUsuario(String correo, String password) {
			super();
			this.correo = correo;
			this.password = password;
		}

	}
	
	@GetMapping("/quieneres")
	public DTO getAutenticado(HttpServletRequest request) {
		DTO dtoUsuaria = new DTO();
		Cookie[] c = request.getCookies();
		int idUsuarioAutenticado = -1;
		for (Cookie co : c) {
		if (co.getName().equals("jwt"))
				// dtoUsuaria.put("id",
       idUsuarioAutenticado = AutenticadorJWT.getIdUsuarioDesdeJWT(co.getValue());
		}
		// identificamos usuario por jwt de cabecera recibida

		// int idUsuarioAutenticado =
		// AutenticadorJWT.getIdUsuarioDesdeJwtIncrustadoEnRequest(request);

		Usuario u = usuRep.findById(idUsuarioAutenticado);
		// dtoUsuaria.put("idfound", idUsuarioAutenticado);
		// si existe el usuario y los datos son correctos, devolveremos un success y
		// todos los datos del usuario
		if (u != null) {
			dtoUsuaria.put("id", u.getId());
			dtoUsuaria.put("nombre", u.getNombre());
			dtoUsuaria.put("correo", u.getCorreo());
			dtoUsuaria.put("password", u.getPassword());
			dtoUsuaria.put("fecha_nac", u.getFechaNac());
			dtoUsuaria.put("rol", u.getRol());
		}

		return dtoUsuaria;
	}
}
