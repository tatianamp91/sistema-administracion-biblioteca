package co.edu.unicatolica.modelo;

import java.util.HashSet;
import java.util.Set;

public class SabEdicion implements java.io.Serializable {

	private static final long serialVersionUID = 7883905253598100198L;

	private Long idEdicion;
	private String descripcion;
	private Long estado;
	private Set<SabLibro> sabLibros = new HashSet<SabLibro>(0);

	public SabEdicion() {
	}

	public SabEdicion(Long idEdicion, String descripcion, Long estado) {
		this.idEdicion = idEdicion;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public SabEdicion(Long idEdicion, String descripcion, Long estado,
			Set<SabLibro> sabLibros) {
		this.idEdicion = idEdicion;
		this.descripcion = descripcion;
		this.estado = estado;
		this.sabLibros = sabLibros;
	}

	public Long getIdEdicion() {
		return this.idEdicion;
	}

	public void setIdEdicion(Long idEdicion) {
		this.idEdicion = idEdicion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Set<SabLibro> getSabLibros() {
		return this.sabLibros;
	}

	public void setSabLibros(Set<SabLibro> sabLibros) {
		this.sabLibros = sabLibros;
	}

}
