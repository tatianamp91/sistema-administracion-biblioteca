package co.edu.unicatolica.modelo;

import java.util.HashSet;
import java.util.Set;

public class SabRol implements java.io.Serializable {

	private static final long serialVersionUID = -8277557232997127222L;

	private Long idRol;
	private String descripcion;
	private Set<SabUsuario> sabUsuarios = new HashSet<SabUsuario>(0);

	public SabRol() {
	}

	public SabRol(Long idRol, String descripcion) {
		this.idRol = idRol;
		this.descripcion = descripcion;
	}

	public SabRol(Long idRol, String descripcion, Set<SabUsuario> sabUsuarios) {
		this.idRol = idRol;
		this.descripcion = descripcion;
		this.sabUsuarios = sabUsuarios;
	}

	public Long getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<SabUsuario> getSabUsuarios() {
		return this.sabUsuarios;
	}

	public void setSabUsuarios(Set<SabUsuario> sabUsuarios) {
		this.sabUsuarios = sabUsuarios;
	}

}
