package proyectoCineSix.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoCineSix.Sala;

@Repository
public interface salaRepositorio extends JpaRepository<Sala, Serializable>{
	
	@Bean
	public abstract List<Sala> findAll();
	public abstract Sala findById(int id);
	@Transactional
	public abstract void deleteById(int id);
	
}
