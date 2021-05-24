package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Table (name="PRODUCTOS")
@Component
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idProducto;
	@Column
	@NotNull(message = "El producto debe tener un codigo")
	private int codigoP;
	@Column
	@NotBlank(message = "El producto debe tener un nombre")
	private String nombreP;
	@Column
	private double precioP;
	@Column
	private String marcaP;
	@Column
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
