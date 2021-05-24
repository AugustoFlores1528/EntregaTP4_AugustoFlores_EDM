package ar.edu.unju.edm.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Entity
@Table (name="CLIENTES")
@Component
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idCliente;
	@Column
	@Min(100000)
	@Max(99999999)
	private int numeroDoc;
	@Column
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private LocalDate fechaNac;
	@Column
	private String tipoDoc;
	@Column
	private int codigoAreaTelef;
	@Column
	private int numTelefono;
	@Column
	@NotBlank(message = "Debe agregar su nombre y apellido")
	private String nombreApellido;
	@Column
    private String password;
	@Column
	private int edad;
	@Column
	private String email;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltCom;
	
	@Column
	private String TDUC;
	@Column
	private String TDFN;
	@Column
	private String THC;

	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}	
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(int numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public int getCodigoAreaTelef() {
		return codigoAreaTelef;
	}

	public void setCodigoAreaTelef(int codigoAreaTelef) {
		this.codigoAreaTelef = codigoAreaTelef;
	}

	public int getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaUltCom() {
		return fechaUltCom;
	}

	public void setFechaUltCom(LocalDate fechaUltCom) {
		this.fechaUltCom = fechaUltCom;
	}

	public int getEdad() {
		LocalDate fechaDeNacimiento=getFechaNac();
		LocalDate fechaActual=LocalDate.now();
		Period periodo=Period.between(fechaDeNacimiento, fechaActual);
		setEdad(periodo.getYears());
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getTDUC() {
		LocalDate fechaCompra = getFechaUltCom();
		LocalDate fechaActual = LocalDate.now();
		Period periodo=Period.between(fechaCompra, fechaActual);
		setTDUC(String.valueOf(periodo.getYears()) +"/"+String.valueOf(periodo.getMonths()) +"/"+String.valueOf(periodo.getDays()));
		return TDUC;
	}

	public void setTDUC(String tDUC) {
		TDUC = tDUC;
	}

	public String getTDFN() {
		LocalDate fechaNacimiento=getFechaNac();
		LocalDate fechaActual=LocalDate.now();
		long diasEntre= ChronoUnit.DAYS.between(fechaNacimiento, fechaActual);
		setTDFN(String.valueOf(diasEntre));
		return TDFN;
	}

	public void setTDFN(String tDFN) {
		TDFN = tDFN;
	}

	public String getTHC() {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaNacimiento=getFechaNac();
		LocalDate sigCump=fechaNacimiento.withYear(fechaActual.getYear());
		
		if(sigCump.isBefore(fechaActual) || sigCump.isEqual(fechaActual)) {
			sigCump=sigCump.plusYears(1);
		}
		
		Period periodo = Period.between(fechaActual, sigCump);
		setTHC(periodo.getDays() +"d/"+ periodo.getMonths() +"m/"+ periodo.getYears() +"a");
		return THC;
	}

	public void setTHC(String tHC) {
		THC = tHC;
	}
	
}