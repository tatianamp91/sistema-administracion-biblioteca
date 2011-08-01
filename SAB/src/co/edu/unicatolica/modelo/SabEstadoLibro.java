package co.edu.unicatolica.modelo;

import java.util.HashSet;
import java.util.Set;

public class SabEstadoLibro implements java.io.Serializable {

	private static final long serialVersionUID = -8014677875006734781L;

	private Long idEstado;
	private String descripcion;
	private Set<SabLibro> sabLibros = new HashSet<SabLibro>(0);

	public SabEstadoLibro() {
	}

	public SabEstadoLibro(Long idEstado, String descripcion) {
		this.idEstado = idEstado;
		this.descripcion = descripcion;
	}

	public SabEstadoLibro(Long idEstado, String descripcion,
			Set<SabLibro> sabLibros) {
		this.idEstado = idEstado;
		this.descripcion = descripcion;
		this.sabLibros = sabLibros;
	}

	public Long getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<SabLibro> getSabLibros() {
		return this.sabLibros;
	}

	public void setSabLibros(Set<SabLibro> sabLibros) {
		this.sabLibros = sabLibros;
	}

}
