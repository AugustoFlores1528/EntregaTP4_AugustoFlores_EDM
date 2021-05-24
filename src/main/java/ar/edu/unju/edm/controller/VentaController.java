package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.model.Venta;
import ar.edu.unju.edm.service.IVentaService;
import ar.edu.unju.edm.service.ProductoService;

@Controller
public class VentaController {
	
	@Autowired
	@Qualifier("impsqlp")
	ProductoService iProductoService;
	
	@Autowired
	Producto productoSeleccionado;
	
	@Autowired
	IVentaService ventaService;
	
	@GetMapping("/producto/ventas")
	public String cargarVentas(Model model) {
		model.addAttribute("productos", iProductoService.obtenerTodosP());
		return("ventas");
	}
	
	@GetMapping("/producto/vender/{codigoP}")	
	public String realizarVenta(Model model, @PathVariable(name="codigoP") Integer codigoP) throws Exception {
		Venta venta = new Venta();		
		try {		
			productoSeleccionado = iProductoService.obtenerProductoCodigo(codigoP);			
			venta = ventaService.crearVenta();		
			venta.setProducto(productoSeleccionado);
			model.addAttribute("venta",venta);
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		return "venta-modal";
	}
}