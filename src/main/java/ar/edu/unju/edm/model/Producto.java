package ar.edu.unju.edm.model;

import org.springframework.stereotype.Component;

@Component
public class Producto {
	private int codigoP;
	private String nombreP;
	private double precioP;
	private String marcaP;
	private int stockP;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigoP() {
		return codigoP;
	}

	public void setCodigoP(int codigoP) {
		this.codigoP = codigoP;
	}

	public String getNombreP() {
		return nombreP;
	}

	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}

	public double getPrecioP() {
		return precioP;
	}

	public void setPrecioP(double precioP) {
		this.precioP = precioP;
	}

	public String getMarcaP() {
		return marcaP;
	}

	public void setMarcaP(String marcaP) {
		this.marcaP = marcaP;
	}

	public int getStockP() {
		return stockP;
	}

	public void setStockP(int stockP) {
		this.stockP = stockP;
	}
	
	
}
