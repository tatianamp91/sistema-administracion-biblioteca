package co.edu.unicatolica.modelo;

import java.util.HashSet;
import java.util.Set;

public class SabArea implements java.io.Serializable {

	private static final long serialVersionUID = 4876076203917251453L;

	private Long idArea;
	private String nombre;
	private Long estado;
	private Set<SabLibro> sabLibros = new HashSet<SabLibro>(0);

	public SabArea() {
	}

	public SabArea(Long idArea, String nombre, Long estado) {
		this.idArea = idArea;
		this.nombre = nombre;
		this.estado = estado;
	}

	public SabArea(Long idArea, String nombre, Long estado,
			Set<SabLibro> sabLibros) {
		this.idArea = idArea;
		this.nombre = nombre;
		this.estado = estado;
		this.sabLibros = sabLibros;
	}

	public Long getIdArea() {
		return this.idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
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
