package proyectoCineSix.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoCineSix.Pelicula;

@Repository
public interface peliculaRepositorio extends JpaRepository<Pelicula, Serializable>{
	
	@Bean
	public abstract List<Pelicula> findAll();
	public abstract Pelicula findById(int id);
	@Transactional
	public abstract void deleteById(int id);
	
}
