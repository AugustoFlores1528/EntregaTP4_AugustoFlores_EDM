package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	@Qualifier("impsql")
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
	public String guardarNuevoCliente(@Valid @ModelAttribute("unCliente") Cliente nuevoCliente,BindingResult resultado, Model model) {
	if (resultado.hasErrors())
	{
		model.addAttribute("unCliente", nuevoCliente);
		model.addAttribute("clientes", clienteService.obtenerTodosC());
		return ("cliente");
	}
	else
	{
	    LOGGER.info("METHOD: ingresando al metodo guardar");	
	    clienteService.guardarC(nuevoCliente);
		LOGGER.info("Tamaño del Listado: "+ clienteService.obtenerTodosC().size());
		return ("redirect:/cliente/mostrar");
	}
	}
	
	@GetMapping("/cliente/eliminarCliente/{id}")
	public String eliminarCliente(Model model, @PathVariable(name="id") int id) {
		try {			clienteService.eliminarCliente(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/cliente/mostrar";
	}
}
