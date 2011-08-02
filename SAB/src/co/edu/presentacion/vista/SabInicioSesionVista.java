package co.edu.presentacion.vista;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabUsuario;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlInputSecret;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabInicioSesionVista {

//	private MensajesView mensaje;
	private HtmlInputText usuario;
	private HtmlInputText contrasenia;

	public SabInicioSesionVista() {
		super();
//		mensaje = (MensajesView) FacesUtils.getManagedBean("mensajesView");
		usuario = new HtmlInputText();
		contrasenia = new HtmlInputSecret();
	}

	public String actionLimpiar() {
		usuario.setValue(null);
		contrasenia.setValue(null);
		return "";
	}

	public String actionIngresar() {
		try {
			if (usuario == null || usuario.getValue() == null || usuario.getValue().toString().trim().equals("")) {
				throw new Exception(FacesUtils.getMensaje("error.inicioSesion.noUsuario"));
			}
			if (contrasenia == null || contrasenia.getValue() == null || contrasenia.getValue().toString().equals("")) {
				throw new Exception(FacesUtils.getMensaje("error.inicioSesion.noContrasenia"));
			}
			
			SabUsuario sabUsuario = DelegadoNegocioVista.consultarUsuarioPorCorreoCodigo(usuario.getValue().toString().trim(), Long.parseLong(contrasenia.getValue().toString()));
			if (sabUsuario == null) {
				throw new Exception(FacesUtils.getMensaje("error.inicioSesion.noValidoCodigo"));
			}
			FacesUtils.putinSession("usuario", sabUsuario);
			
			if(sabUsuario.getSabRol().getIdRol().equals(Long.parseLong(FacesUtils.getParametros("rolAdministrador")))){
				return "goPrestamo";
			}else{
				return "goConsulta";
			}
		} catch (Exception e) {
//			mensaje.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String actionIngresarInvitado() {
		try {
			SabUsuario sapUsuario = new SabUsuario();
			sapUsuario.setIdUsuario(0L);
			sapUsuario.setNombreCompleto("Invitado");
			FacesUtils.putinSession("usuario", sapUsuario);
		} catch (Exception e) {
//			mensaje.addErrorMessage(e.getMessage());
		}
		return "goConsulta";
	}

	public HtmlInputText getUsuario() {
		return usuario;
	}

	public void setUsuario(HtmlInputText usuario) {
		this.usuario = usuario;
	}

	public HtmlInputText getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(HtmlInputText contrasenia) {
		this.contrasenia = contrasenia;
	}

}
