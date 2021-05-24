package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Producto;

@Repository
public interface IProductoDAO extends CrudRepository<Producto, Integer>{

		@Query("from Producto p order by p.codigoP")
		public List<Producto> obtenerProductos();
			
		public Optional<Producto> findByCodigoP(int cod);
	}