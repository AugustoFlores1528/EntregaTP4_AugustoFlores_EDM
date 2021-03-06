package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Venta;

public interface IVentaService {
	public void guardarVenta(Venta unaVenta);
	public Venta crearVenta();
	public List<Venta> obtenerTodaVenta();
	public Venta encontrarUnaVenta (int id) throws Exception;
}
