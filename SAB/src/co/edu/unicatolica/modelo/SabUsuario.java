package co.edu.unicatolica.modelo;

import java.util.HashSet;
import java.util.Set;

public class SabUsuario implements java.io.Serializable {

	private static final long serialVersionUID = 4792963055359626372L;

	private Long idUsuario;
	private SabRol sabRol;
	private Long numIdentificacion;
	private String nombreCompleto;
	private Long codigo;
	private String email;
	private Set<SabPrestamo> sabPrestamos = new HashSet<SabPrestamo>(0);

	public SabUsuario() {
	}

	public SabUsuario(Long idUsuario, Long numIdentificacion,
			String nombreCompleto, Long codigo, String email) {
		this.idUsuario = idUsuario;
		this.numIdentificacion = numIdentificacion;
		this.nombreCompleto = nombreCompleto;
		this.codigo = codigo;
		this.email = email;
	}

	public SabUsuario(Long idUsuario, SabRol sabRol, Long numIdentificacion,
			String nombreCompleto, Long codigo, String email,
			Set<SabPrestamo> sabPrestamos) {
		this.idUsuario = idUsuario;
		this.sabRol = sabRol;
		this.numIdentificacion = numIdentificacion;
		this.nombreCompleto = nombreCompleto;
		this.codigo = codigo;
		this.email = email;
		this.sabPrestamos = sabPrestamos;
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public SabRol getSabRol() {
		return this.sabRol;
	}

	public void setSabRol(SabRol sabRol) {
		this.sabRol = sabRol;
	}

	public Long getNumIdentificacion() {
		return this.numIdentificacion;
	}

	public void setNumIdentificacion(Long numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<SabPrestamo> getSabPrestamos() {
		return this.sabPrestamos;
	}

	public void setSabPrestamos(Set<SabPrestamo> sabPrestamos) {
		this.sabPrestamos = sabPrestamos;
	}

}
