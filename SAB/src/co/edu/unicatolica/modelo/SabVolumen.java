package co.edu.unicatolica.modelo;

import java.util.HashSet;
import java.util.Set;

public class SabVolumen implements java.io.Serializable {

	private static final long serialVersionUID = -3534981145017251193L;

	private Long idVolumen;
	private String descripcion;
	private Long estado;
	private Set<SabLibro> sabLibros = new HashSet<SabLibro>(0);

	public SabVolumen() {
	}

	public SabVolumen(Long idVolumen, String descripcion, Long estado) {
		this.idVolumen = idVolumen;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public SabVolumen(Long idVolumen, String descripcion, Long estado,
			Set<SabLibro> sabLibros) {
		this.idVolumen = idVolumen;
		this.descripcion = descripcion;
		this.estado = estado;
		this.sabLibros = sabLibros;
	}

	public Long getIdVolumen() {
		return this.idVolumen;
	}

	public void setIdVolumen(Long idVolumen) {
		this.idVolumen = idVolumen;
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
