package co.edu.unicatolica.modelo;

import java.util.HashSet;
import java.util.Set;

public class SabAutor implements java.io.Serializable {

	private static final long serialVersionUID = 7383853644708638393L;
	
	private Long idAutor;
	private String nombre;
	private Set<SabLibroAutor> sabLibroAutors = new HashSet<SabLibroAutor>(0);

	public SabAutor() {
	}

	public SabAutor(Long idAutor, String nombre) {
		this.idAutor = idAutor;
		this.nombre = nombre;
	}

	public SabAutor(Long idAutor, String nombre,
			Set<SabLibroAutor> sabLibroAutors) {
		this.idAutor = idAutor;
		this.nombre = nombre;
		this.sabLibroAutors = sabLibroAutors;
	}

	public Long getIdAutor() {
		return this.idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<SabLibroAutor> getSabLibroAutors() {
		return sabLibroAutors;
	}

	public void setSabLibroAutors(Set<SabLibroAutor> sabLibroAutors) {
		this.sabLibroAutors = sabLibroAutors;
	}

}
