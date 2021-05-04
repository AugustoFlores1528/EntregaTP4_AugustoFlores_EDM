package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Cliente;

public interface IClienteService {
	public void guardarC(Cliente unCliente);
	public Cliente crearC();
	public List<Cliente> obtenerTodosC();
	public Cliente encontrarUnCliente(int dni);
	public void modificarCliente(Cliente clienteModificado);
}
