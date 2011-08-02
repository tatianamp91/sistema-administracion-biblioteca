package co.edu.presentacion.vista;

import java.util.List;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabPrestamo;
import co.edu.utilities.FacesUtils;

public class SabConsultaPrestamoVista  {
	
	private SabMensajesVista mensaje;
	
	private List<SabPrestamo> listConsulta;
	
	public SabConsultaPrestamoVista() {
		try {
			mensaje = (SabMensajesVista) FacesUtils.getManagedBean("sabMensajesVista");
			
			String idUsuario = FacesUtils.getUserCodigo();
			
			listConsulta = DelegadoNegocioVista.buscarPorUsuarioLibro(null, idUsuario);
			
		} catch (Exception e) {
			mensaje.addErrorMessage(e.getMessage());
		}
    }

	public List<SabPrestamo> getListConsulta() {
		return listConsulta;
	}

	public void setListConsulta(List<SabPrestamo> listConsulta) {
		this.listConsulta = listConsulta;
	}
	
}