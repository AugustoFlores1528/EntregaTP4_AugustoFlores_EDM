package ar.edu.unju.edm.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.repository.IClienteDAO;
import ar.edu.unju.edm.service.IClienteService;

@Service
@Qualifier("impsql")
public class ClienteServiceMySQL implements IClienteService{
	
	@Autowired
	Cliente unCliente;
	
	@Autowired
	IClienteDAO clienteDAO;
	
	@Override
	public void guardarC(Cliente unCliente) {
		// TODO Auto-generated method stub
		clienteDAO.save(unCliente);
	}

	@Override
	public Cliente crearC() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosC() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	public Cliente encontrarUnCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		return clienteDAO.findByNumeroDoc(dni).orElseThrow(()->new Exception ("El cliente NO existe"));
	}

	@Override
	public void modificarCliente(Cliente unClienteModificado) throws Exception{
		// TODO Auto-generated method stub
        Cliente clienteAModificar = clienteDAO.findByNumeroDoc(unClienteModificado.getNumeroDoc()).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
		cambiarCliente(unClienteModificado, clienteAModificar);
		
		clienteDAO.save(clienteAModificar);
	}
	
	private void cambiarCliente(Cliente desde, Cliente hacia) {
		// TODO Auto-generated method stub
		hacia.setNumeroDoc(desde.getNumeroDoc());
		hacia.setNombreApellido(desde.getNombreApellido());
		hacia.setTipoDoc(desde.getTipoDoc());
		hacia.setFechaNac(desde.getFechaNac());
		hacia.setCodigoAreaTelef(desde.getCodigoAreaTelef());
		hacia.setNumTelefono(desde.getNumTelefono());
		hacia.setEmail(desde.getEmail());
	}

	@Override
	public void eliminarCliente(int dni) throws Exception{
		// TODO Auto-generated method stub
		Cliente clienteEliminar = clienteDAO.findByNumeroDoc(dni).orElseThrow(()->new Exception("El cliente no fue encontrado"));
		clienteDAO.delete(clienteEliminar);
	}

}
