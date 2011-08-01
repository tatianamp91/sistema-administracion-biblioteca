package co.edu.unicatolica.modelo;

import java.util.HashSet;
import java.util.Set;

public class SabLibro implements java.io.Serializable {

	private static final long serialVersionUID = 5865044607049030575L;
	
	private Long idLibro;
	private SabVolumen sabVolumen;
	private SabEditorial sabEditorial;
	private SabArea sabArea;
	private SabEdicion sabEdicion;
	private SabEstadoLibro sabEstadoLibro;
	private String titulo;
	private Long cantidad;
	private Long cantidadPrestados;
	private Set<SabPrestamo> sabPrestamos = new HashSet<SabPrestamo>(0);
	private Set<SabLibroAutor> sabLibroAutors = new HashSet<SabLibroAutor>(0);

	public SabLibro() {
	}

	public SabLibro(Long idLibro, String titulo, Long cantidad,
			Long cantidadPrestados) {
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.cantidad = cantidad;
		this.cantidadPrestados = cantidadPrestados;
	}

	public SabLibro(Long idLibro, SabVolumen sabVolumen,
			SabEditorial sabEditorial, SabArea sabArea, SabEdicion sabEdicion,
			SabEstadoLibro sabEstadoLibro, String titulo, Long cantidad,
			Long cantidadPrestados, Set<SabPrestamo> sabPrestamos,
			Set<SabLibroAutor> sabLibroAutors) {
		this.idLibro = idLibro;
		this.sabVolumen = sabVolumen;
		this.sabEditorial = sabEditorial;
		this.sabArea = sabArea;
		this.sabEdicion = sabEdicion;
		this.sabEstadoLibro = sabEstadoLibro;
		this.titulo = titulo;
		this.cantidad = cantidad;
		this.cantidadPrestados = cantidadPrestados;
		this.sabPrestamos = sabPrestamos;
		this.sabLibroAutors = sabLibroAutors;
	}

	public Long getIdLibro() {
		return this.idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public SabVolumen getSabVolumen() {
		return this.sabVolumen;
	}

	public void setSabVolumen(SabVolumen sabVolumen) {
		this.sabVolumen = sabVolumen;
	}

	public SabEditorial getSabEditorial() {
		return this.sabEditorial;
	}

	public void setSabEditorial(SabEditorial sabEditorial) {
		this.sabEditorial = sabEditorial;
	}

	public SabArea getSabArea() {
		return this.sabArea;
	}

	public void setSabArea(SabArea sabArea) {
		this.sabArea = sabArea;
	}

	public SabEdicion getSabEdicion() {
		return this.sabEdicion;
	}

	public void setSabEdicion(SabEdicion sabEdicion) {
		this.sabEdicion = sabEdicion;
	}

	public SabEstadoLibro getSabEstadoLibro() {
		return this.sabEstadoLibro;
	}

	public void setSabEstadoLibro(SabEstadoLibro sabEstadoLibro) {
		this.sabEstadoLibro = sabEstadoLibro;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getCantidadPrestados() {
		return this.cantidadPrestados;
	}

	public void setCantidadPrestados(Long cantidadPrestados) {
		this.cantidadPrestados = cantidadPrestados;
	}

	public Set<SabPrestamo> getSabPrestamos() {
		return this.sabPrestamos;
	}

	public void setSabPrestamos(Set<SabPrestamo> sabPrestamos) {
		this.sabPrestamos = sabPrestamos;
	}

	public Set<SabLibroAutor> getSabLibroAutors() {
		return sabLibroAutors;
	}

	public void setSabLibroAutors(Set<SabLibroAutor> sabLibroAutors) {
		this.sabLibroAutors = sabLibroAutors;
	}

}
