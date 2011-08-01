package co.edu.unicatolica.modelo;

import java.util.Date;

public class SabPrestamo implements java.io.Serializable {

	private static final long serialVersionUID = 8955528917150487857L;

	private Long idPrestamo;
	private SabUsuario sabUsuario;
	private SabLibro sabLibro;
	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private Long estadoPrestamo;
	private Date fechaRealDevolucion;

	public SabPrestamo() {
		
	}

	public SabPrestamo(Long idPrestamo, SabUsuario sabUsuario,
			SabLibro sabLibro, Date fechaPrestamo, Date fechaDevolucion,
			Long estadoPrestamo, Date fechaRealDevolucion) {
		this.idPrestamo = idPrestamo;
		this.sabUsuario = sabUsuario;
		this.sabLibro = sabLibro;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.estadoPrestamo = estadoPrestamo;
		this.fechaRealDevolucion = fechaRealDevolucion;
	}

	
	public Long getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(Long idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public SabUsuario getSabUsuario() {
		return this.sabUsuario;
	}

	public void setSabUsuario(SabUsuario sabUsuario) {
		this.sabUsuario = sabUsuario;
	}

	public SabLibro getSabLibro() {
		return this.sabLibro;
	}

	public void setSabLibro(SabLibro sabLibro) {
		this.sabLibro = sabLibro;
	}

	public Date getFechaPrestamo() {
		return this.fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaDevolucion() {
		return this.fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Long getEstadoPrestamo() {
		return this.estadoPrestamo;
	}

	public void setEstadoPrestamo(Long estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public Date getFechaRealDevolucion() {
		return this.fechaRealDevolucion;
	}

	public void setFechaRealDevolucion(Date fechaRealDevolucion) {
		this.fechaRealDevolucion = fechaRealDevolucion;
	}

}
