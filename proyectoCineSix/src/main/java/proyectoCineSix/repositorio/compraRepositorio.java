package proyectoCineSix.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyectoCineSix.Compra;

@Repository

public interface compraRepositorio extends JpaRepository<Compra, Serializable>{
	@Bean
	public abstract List<Compra> findAll();
	public abstract Compra findById(int id);

	
	@Transactional
	public abstract void deleteById(int id);
	
}