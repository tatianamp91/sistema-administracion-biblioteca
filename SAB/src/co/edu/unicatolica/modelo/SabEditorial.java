package co.edu.unicatolica.modelo;

import java.util.HashSet;
import java.util.Set;

public class SabEditorial implements java.io.Serializable {

	private static final long serialVersionUID = -1787248191001858701L;

	private Long idEditorial;
	private String nombre;
	private Long estado;
	private Set<SabLibro> sabLibros = new HashSet<SabLibro>(0);

	public SabEditorial() {
	}

	public SabEditorial(Long idEditorial, String nombre, Long estado) {
		this.idEditorial = idEditorial;
		this.nombre = nombre;
		this.estado = estado;
	}

	public SabEditorial(Long idEditorial, String nombre, Long estado,
			Set<SabLibro> sabLibros) {
		this.idEditorial = idEditorial;
		this.nombre = nombre;
		this.estado = estado;
		this.sabLibros = sabLibros;
	}

	public Long getIdEditorial() {
		return this.idEditorial;
	}

	public void setIdEditorial(Long idEditorial) {
		this.idEditorial = idEditorial;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
