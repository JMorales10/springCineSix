package proyectoCineSix.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoCineSix.Entrada;

@Repository
public interface entradaRepositorio extends JpaRepository<Entrada, Serializable>{
	
	@Bean
	public abstract List<Entrada> findAll();
	public abstract Entrada findById(int id);
	@Transactional
	public abstract void deleteById(int id);
	
}
