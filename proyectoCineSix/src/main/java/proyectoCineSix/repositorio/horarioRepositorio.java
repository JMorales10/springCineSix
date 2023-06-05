package proyectoCineSix.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoCineSix.Horario;

@Repository

public interface horarioRepositorio extends JpaRepository<Horario, Serializable>{
	@Bean
	public abstract List<Horario> findAll();
	public abstract Horario findById(int id);

	
	@Transactional
	public abstract void deleteById(int id);
	
}