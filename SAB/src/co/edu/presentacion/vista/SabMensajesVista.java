package co.edu.presentacion.vista;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

import co.edu.utilities.FacesUtils;

public class SabMensajesVista {
	private boolean visible = false;
	private String mensaje = "";
	
	private String messageImage = "";
	private String messageStyle = "";
	private String messageTitulo = "";
	
	public SabMensajesVista(){
		super();
	}
	
	public void addErrorMessage(String mensaje) {
		this.mensaje = mensaje;
		visible = true;
		messageImage = "";
		messageStyle = "";
		messageTitulo = "";
		FacesUtils.addErrorMessage(mensaje);
	}
	
	public void addInfoMessage(String mensaje) {
		this.mensaje = mensaje;
		visible = true;
		messageImage = "";
		messageStyle = "";
		messageTitulo = "";
		FacesUtils.addInfoMessage(mensaje);
	}
	
	public void mostrarMensaje(String mensaje) {
		// Muestra el mensaje en el panel popup
		this.mensaje = mensaje;
		visible = true;
		messageImage = "";
		messageStyle = "";
		messageTitulo = "";
		FacesUtils.addInfoMessage(mensaje);
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String ocultarMensaje(){
		this.mensaje = "";
		this.visible = false;
		messageImage = "";
		messageStyle = "";
		messageTitulo = "";
		return "";
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setMessageImage(String messageImage) {
		this.messageImage = messageImage;
	}

	public String getMessageImage() {
		// See if there are messages queued for the page
		Severity severityLevel = FacesUtils.getFacesContext().getMaximumSeverity();
		if (messageImage.isEmpty() && null != severityLevel) {
			if (severityLevel.equals(FacesMessage.SEVERITY_ERROR)) {
				messageImage = "/images/error.png";
			} else if (severityLevel.equals(FacesMessage.SEVERITY_INFO)) {
				messageImage = "/images/success.png";
			} else if (severityLevel.equals(FacesMessage.SEVERITY_WARN)) {
				messageImage = "/images/warn.png";
			} else if (severityLevel.equals(FacesMessage.SEVERITY_FATAL)) {
				messageImage = "/images/error.png";
			}
		}
		return messageImage;
	}

	public void setMessageStyle(String messageStyle) {
		this.messageStyle = messageStyle;
	}

	public String getMessageStyle() {
		// See if there are messages queued for the page
		Severity severityLevel = FacesUtils.getFacesContext().getMaximumSeverity();
		if (messageStyle.isEmpty() && null != severityLevel) {
			if (severityLevel.equals(FacesMessage.SEVERITY_ERROR)) {
				messageStyle = "msg-error";
			} else if (severityLevel.equals(FacesMessage.SEVERITY_INFO)) {
				messageStyle = "msg-success";
			} else if (severityLevel.equals(FacesMessage.SEVERITY_WARN)) {
				messageStyle = "msg-alert";
			} else if (severityLevel.equals(FacesMessage.SEVERITY_FATAL)) {
				messageStyle = "msg-error";
			}
		}
		return messageStyle;
	}

	public String getMessageTitulo() {
		// See if there are messages queued for the page
		Severity severityLevel = FacesUtils.getFacesContext().getMaximumSeverity();
		if (messageTitulo.isEmpty() && null != severityLevel) {
			if (severityLevel.equals(FacesMessage.SEVERITY_ERROR)) {
				messageTitulo = "Error";
			} else if (severityLevel.equals(FacesMessage.SEVERITY_INFO)) {
				messageTitulo = "Mensaje";
			} else if (severityLevel.equals(FacesMessage.SEVERITY_WARN)) {
				messageTitulo = "Alerta";
			} else if (severityLevel.equals(FacesMessage.SEVERITY_FATAL)) {
				messageTitulo = "Error Fatal";
			}
		}
		return messageTitulo;
	}

	public void setMessageTitulo(String messageTitulo) {
		this.messageTitulo = messageTitulo;
	}
	
}