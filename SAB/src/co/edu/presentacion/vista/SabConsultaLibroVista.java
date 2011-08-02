package co.edu.presentacion.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.icesoft.faces.component.ext.HtmlInputText;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabArea;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.utilities.FacesUtils;

public class SabConsultaLibroVista  {
	
	private SabMensajesVista mensaje;
	
	private HtmlInputText idLibro;
	private String titulo;
	private Long idArea;
	private String autor;
	
	private List<SelectItem> listArea;
	private List<SabLibro> listConsulta;
	
	public SabConsultaLibroVista() {
		try {
			mensaje = (SabMensajesVista) FacesUtils.getManagedBean("sabMensajesVista");
			
			idLibro = new HtmlInputText();
			
			listArea = new ArrayList<SelectItem>();
	       	List<SabArea> areas = DelegadoNegocioVista.getSabArea();
	       	listArea.add(new SelectItem (0L,"Seleccione"));
	       	for(SabArea sabArea : areas){
	       		listArea.add(new SelectItem(sabArea.getIdArea(), sabArea.getNombre()));
	       	}
	       	
		} catch (Exception e) {
			mensaje.addErrorMessage(e.getMessage());
		}
    }
	
	public String action_consultar(){
		try{
			if(FacesUtils.checkLong(idLibro) == null 
				&& (titulo == null || titulo.isEmpty()) 
				&& (idArea == null || idArea.equals(0L)) 
				&& (autor == null || autor.isEmpty())){
				throw new Exception(FacesUtils.getMensaje("error.consulta.no.filtro"));
			}
			
			listConsulta = DelegadoNegocioVista.consultarLibrosFiltro(FacesUtils.checkLong(idLibro), titulo, idArea, autor);
			
			if(listConsulta == null || (listConsulta != null && listConsulta.isEmpty())){
				throw new Exception(FacesUtils.getMensaje("error.consulta.no.encontrada"));
			}
		} catch (Exception e) {
			action_clear();
			mensaje.addErrorMessage(e.getMessage());
		}
		return "";
	}

    public String action_clear() {
    	idLibro.setValue(null);
    	titulo = "";
    	idArea = null;
    	autor = "";
    	
    	listConsulta = new ArrayList<SabLibro>();
        return "";
    }

	public List<SabLibro> getListConsulta() {
		return listConsulta;
	}

	public void setListConsulta(List<SabLibro> listConsulta) {
		this.listConsulta = listConsulta;
	}

	public HtmlInputText getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(HtmlInputText idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public List<SelectItem> getListArea() {
		return listArea;
	}

	public void setListArea(List<SelectItem> listArea) {
		this.listArea = listArea;
	}
    
}