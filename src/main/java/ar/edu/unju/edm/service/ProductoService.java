package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;

@Service
public interface ProductoService {
	
	public void guardarP(Producto unProducto);
	public Producto obtenerProductoCodigo (Integer codigoP);
	public Producto obtenerUnP(String nombreP);
	public List<Producto> obtenerTodosP();
	public Producto obtenerNuevoP();
	public Producto encontrarUnProducto(int cod) throws Exception;
	public void modificarProducto(Producto productoModificado) throws Exception;
	public void eliminarProducto(int id) throws Exception;
	Producto obtenerUltimoP();
	
}
