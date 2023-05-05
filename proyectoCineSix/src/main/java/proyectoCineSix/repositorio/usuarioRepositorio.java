package proyectoCineSix.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoCineSix.Usuario;

@Repository
public interface usuarioRepositorio extends JpaRepository<Usuario, Serializable>{
	
	@Bean
	public abstract List<Usuario> findAll();
	public abstract Usuario findById(int id);
	public abstract Usuario findByNombre(String dni);
	public abstract Usuario findByCorreoAndClave(String correo, String clave);
	@Transactional
	public abstract void deleteById(int id);
	
}
