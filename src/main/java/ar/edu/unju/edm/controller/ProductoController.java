package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.ProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	@Qualifier("impsqlp")
	ProductoService iProductoService;
	
	@GetMapping("/producto/mostrar")
	public String cargarP(Model model) {
		model.addAttribute("unProducto", iProductoService.obtenerNuevoP());
		model.addAttribute("productos", iProductoService.obtenerTodosP());
		return ("producto");
	}
	
	@GetMapping("/producto/editar/{codigoP}")
	public String editarProducto(Model model, @PathVariable(name="codigoP") int cod) throws Exception {
		try {
			Producto productoEncontrado = iProductoService.encontrarUnProducto(cod);
			model.addAttribute("unProducto", productoEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", iProductoService.obtenerNuevoP());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("productos", iProductoService.obtenerTodosP());
		return("producto");
	}
	
	@PostMapping("/producto/modificar")
	public String modificarProducto(@ModelAttribute("unProducto") Producto productoModificado, Model model) {
		try {
			iProductoService.modificarProducto(productoModificado);
			model.addAttribute("unProducto", new Producto());				
			model.addAttribute("editMode", "false");
		} 
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoModificado);			
			model.addAttribute("productos", iProductoService.obtenerTodosP());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("productos", iProductoService.obtenerTodosP());
		return("producto");
	}
	
	@PostMapping("/producto/guardar")
	public String guardarNuevoProducto(@Valid @ModelAttribute("unProducto") Producto nuevoProducto,BindingResult resultadoP, Model model) {
		if (resultadoP.hasErrors())
		{
			model.addAttribute("unProducto", nuevoProducto);
			model.addAttribute("clientes", iProductoService.obtenerTodosP());
			return ("producto");
		}
		else
		{
		    iProductoService.guardarP(nuevoProducto);
			return ("redirect:/producto/mostrar");
		}
	}
	
	@GetMapping("/ultimo")
	public String cargarUltimoP(Model model) {
		model.addAttribute("ultimoProducto", iProductoService.obtenerUltimoP());
		return("mostrar-ultimo");
	}

	@GetMapping("/volver")
	public String cargarNuevoP(Model model) {;
		return("redirect:/producto");
	}
	
	@GetMapping("/producto/eliminarProducto/{id}")
	public String eliminarProducto(Model model, @PathVariable(name="id") int id) {
		try {			iProductoService.eliminarProducto(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/producto/mostrar";
	}
	
}
