package ar.edu.unju.edm.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.repository.IProductoDAO;
import ar.edu.unju.edm.service.ProductoService;

@Service
@Qualifier ("impsqlp")
public class ProductoServiceMySQL implements ProductoService{
	
	@Autowired
	Producto unProducto;
	
	@Autowired
	IProductoDAO productoDAO;
	
	@Override
	public void guardarP(Producto unProducto) {
		// TODO Auto-generated method stub
		productoDAO.save(unProducto);
	}
	
	@Override
	public Producto obtenerProductoCodigo(Integer codigoP) {
		// TODO Auto-generated method stub
		return productoDAO.findByCodigoP(codigoP).orElseThrow();
	}

	@Override
	public Producto obtenerUnP(String nombreP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerTodosP() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoDAO.findAll();
	}

	@Override
	public Producto obtenerNuevoP() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public Producto encontrarUnProducto(int cod) throws Exception {
		// TODO Auto-generated method stub
		return productoDAO.findByCodigoP(cod).orElseThrow(()->new Exception ("El producto NO existe"));
	}

	@Override
	public void modificarProducto(Producto unProductoModificado) throws Exception {
		// TODO Auto-generated method stub
		Producto productoAModificar = productoDAO.findByCodigoP(unProductoModificado.getCodigoP()).orElseThrow(()->new Exception("El Producto no fue encontrado"));
		cambiarProducto(unProductoModificado, productoAModificar);
		
		productoDAO.save(productoAModificar);
	}
	
	private void cambiarProducto(Producto desde, Producto hacia) {
		// TODO Auto-generated method stub
		hacia.setCodigoP(desde.getCodigoP());
		hacia.setNombreP(desde.getNombreP());
		hacia.setPrecioP(desde.getPrecioP());
		hacia.setMarcaP(desde.getMarcaP());
		hacia.setStockP(desde.getStockP());
	}
	
	@Override
	public void eliminarProducto(int cod) throws Exception{
		// TODO Auto-generated method stub
		Producto productoEliminar = productoDAO.findByCodigoP(cod).orElseThrow(()->new Exception("El producto no fue encontrado"));
		productoDAO.delete(productoEliminar);
	}

	@Override
	public Producto obtenerUltimoP() {
		// TODO Auto-generated method stub
		return null;
	}

}
