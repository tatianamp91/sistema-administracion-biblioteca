package co.edu.presentacion.vista;

import co.edu.unicatolica.modelo.SabRol;
import co.edu.utilities.FacesUtils;

public class SabMenuVista {
	
	private boolean administrador;
	private boolean profesorEstudiante;
	private boolean otro;
	private String userName;
	
	public SabMenuVista() {
		try {
			SabRol rol = FacesUtils.getUserRol(); 
			administrador = false;
			profesorEstudiante = false;
			otro = false;
			
			if(rol != null){
				if(rol.getIdRol().equals(Long.parseLong(FacesUtils.getParametros("rolAdministrador")))){
					administrador = true;
				}else if(rol.getIdRol().equals(Long.parseLong(FacesUtils.getParametros("rolEstudiante")))
					|| rol.getIdRol().equals(Long.parseLong(FacesUtils.getParametros("rolProfesor")))){
					profesorEstudiante = true;
				}else if(rol.getIdRol().equals(Long.parseLong(FacesUtils.getParametros("rolOtros")))){
					otro = true;
				}
			}
				
			userName = FacesUtils.getUserName();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String cerrarSesion(){
		FacesUtils.putinSession("usuario", null);
		return "goInicio";
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public boolean isProfesorEstudiante() {
		return profesorEstudiante;
	}

	public void setProfesorEstudiante(boolean profesorEstudiante) {
		this.profesorEstudiante = profesorEstudiante;
	}

	public boolean isOtro() {
		return otro;
	}

	public void setOtro(boolean otro) {
		this.otro = otro;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
