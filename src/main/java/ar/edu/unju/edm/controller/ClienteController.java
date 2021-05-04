package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@Controller
public class ClienteController {
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	
	@Autowired
	IClienteService clienteService;
	
	@GetMapping("/cliente/mostrar")
	public String cargarC(Model model) {
		model.addAttribute("unCliente", clienteService.crearC());
		model.addAttribute("clientes", clienteService.obtenerTodosC());
		return("cliente");
	}
	
	@GetMapping("/cliente/editar/{numeroDoc}")
	public String editarCliente(Model model, @PathVariable(name="numeroDoc") int dni) throws Exception {
		try {
			Cliente clienteEncontrado = clienteService.encontrarUnCliente(dni);
			model.addAttribute("unCliente", clienteEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unCliente", clienteService.crearC());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("clientes", clienteService.obtenerTodosC());
		return("cliente");
	}
	
	@PostMapping("/cliente/modificar")
	public String modificarCliente(@ModelAttribute("unCliente") Cliente clienteModificado, Model model) {
		try {
			clienteService.modificarCliente(clienteModificado);
			model.addAttribute("unCliente", new Cliente());
			model.addAttribute("editMode","false");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unCliente", clienteModificado);
			model.addAttribute("clientes", clienteService.obtenerTodosC());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("clientes", clienteService.obtenerTodosC());
		return ("cliente");
	}
	
	@PostMapping("/cliente/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unCliente") Cliente nuevoCliente, Model model) {
		LOGGER.info("METHOD: ingresando al metodo guardar");
		clienteService.guardarC(nuevoCliente);
		LOGGER.info("Tamaño del Listado: "+ clienteService.obtenerTodosC().size());
		
		return ("redirect:/cliente/mostrar");
	}
}
