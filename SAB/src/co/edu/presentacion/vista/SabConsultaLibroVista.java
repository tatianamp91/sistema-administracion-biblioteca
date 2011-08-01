package co.edu.presentacion.vista;

import java.util.List;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.utilities.FacesUtils;

public class SabConsultaLibroVista  {
	private List<SabLibro> listConsulta;

	
	public SabConsultaLibroVista() {
		try {

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    }

    public String action_clear() {
        return "";
    }

	public List<SabLibro> getListConsulta() {
		try {
			listConsulta = DelegadoNegocioVista.getSabLibro();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listConsulta;
	}

	public void setListConsulta(List<SabLibro> listConsulta) {
		this.listConsulta = listConsulta;
	}
    
}