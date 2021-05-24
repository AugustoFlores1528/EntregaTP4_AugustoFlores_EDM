package ar.edu.unju.edm.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.ProductoService;
import ar.edu.unju.edm.util.ListadoProductos;

@Service
public class ProductoServiceIMP implements ProductoService{
	
	private static final Log LOGGER = LogFactory.getLog(ProductoServiceIMP.class);
	
	@Autowired
	Producto unProducto;
	
	//ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();
	private List<Producto> listaDeProductos = ListadoProductos.productos;
	
	@Override
	public void guardarP(Producto unProducto) {
		// TODO Auto-generated method stub
		System.out.println(unProducto.getNombreP());
		listaDeProductos.add(unProducto);
		System.out.println(listaDeProductos.size());
		
		LOGGER.info("METHOD: Ingresando a Guardar Producto");
		LOGGER.info("RESULT: guardado" + listaDeProductos.get(listaDeProductos.size()-1).getNombreP());
	}

	@Override
	public Producto obtenerUnP(String nombreP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerTodosP() {
		// TODO Auto-generated method stub
		return listaDeProductos;
	}

	@Override
	public Producto obtenerNuevoP() {
		// TODO Auto-generated method stub
		return unProducto;
	}
	
	@Override
	public Producto obtenerUltimoP() {
		// TODO Auto-generated method stub
		int i = listaDeProductos.size() - 1;
		return listaDeProductos.get(i);
	}

	@Override
	public Producto encontrarUnProducto(int cod) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDeProductos.size(); i++){
		    if (listaDeProductos.get(i).getCodigoP() == cod) {
		    	unProducto = listaDeProductos.get(i);
		    }
		}
		return unProducto;
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
				for (int i = 0; i < listaDeProductos.size(); i++){
				    if (listaDeProductos.get(i).getCodigoP() == productoModificado.getCodigoP()) {
				    	listaDeProductos.set(i, productoModificado);
				    }
				}
	}

	@Override
	public void eliminarProducto(int id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDeProductos.size(); i++){
		    if (listaDeProductos.get(i).getCodigoP() == id) {
		    	listaDeProductos.remove(i);
		    }
		}
	}

	@Override
	public Producto obtenerProductoCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
