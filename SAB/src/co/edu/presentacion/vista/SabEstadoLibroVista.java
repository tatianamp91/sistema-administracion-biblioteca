package co.edu.presentacion.vista;

import java.util.List;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabEstadoLibro;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabEstadoLibroVista  {
	
	private SabMensajesVista mensaje;
	
    private HtmlInputText txtDescripcion;
    private Long idEstado;
    private HtmlCommandButton btnSave;
    private HtmlCommandButton btnModify;
    private HtmlCommandButton btnDelete;
    private HtmlCommandButton btnClear;
    private List<SabEstadoLibro> sabEstadoLibro;

    public SabEstadoLibroVista() {

    	mensaje = (SabMensajesVista) FacesUtils.getManagedBean("sabMensajesVista");

    	txtDescripcion = new HtmlInputText();
    	btnSave = new HtmlCommandButton();
    	btnModify = new HtmlCommandButton();
    	btnDelete = new HtmlCommandButton();
    	btnClear = new HtmlCommandButton();
    	
    	action_clear();
    }

    public String action_clear() {
        txtDescripcion.setValue(null);
        btnSave.setDisabled(false);
        btnDelete.setDisabled(true);
        btnModify.setDisabled(true);
        btnClear.setDisabled(false);
        return "";
    }

 
    public String action_save() {
        try {
            DelegadoNegocioVista.saveSabEstadoLibro(FacesUtils.checkString(
                    txtDescripcion));
            mensaje.addInfoMessage(FacesUtils.getMensaje("estadoLibro.guardado"));
            action_clear();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete() {
        try {
            DelegadoNegocioVista.deleteSabEstadoLibro(idEstado);
            mensaje.addInfoMessage(FacesUtils.getMensaje("estadoLibro.eliminado"));
            action_clear();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String  action_commandLink(){
    	try{
    		SabEstadoLibro sabEstadoLibroo = DelegadoNegocioVista.getSabEstadoLibro(idEstado);
	   		if(sabEstadoLibroo.getDescripcion() != null){
	   			txtDescripcion.setValue(sabEstadoLibroo.getDescripcion());
	   			btnModify.setDisabled(false);
	   	        btnSave.setDisabled(true);
	   	        btnDelete.setDisabled(false);
	   	        btnModify.setDisabled(false);
	   	        btnClear.setDisabled(false);
    		}else{
    			throw new Exception(FacesUtils.getMensaje("error.estadoLibro.no.encontrado"));
    		}
    	}catch (Exception e){
    		mensaje.addErrorMessage(e.getMessage());	
    	}
    	return "";
 	}
    public String action_modify() {
        try {
            DelegadoNegocioVista.updateSabEstadoLibro(FacesUtils.checkString(
                    txtDescripcion), idEstado);
            mensaje.addInfoMessage(FacesUtils.getMensaje("estadoLibro.modificado"));
            action_clear();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }
        return "";
    }

     public List<SabEstadoLibro> getSabEstadoLibro() {
        try {
            sabEstadoLibro = DelegadoNegocioVista.getSabEstadoLibro();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }
        return sabEstadoLibro;
    }

    public void setSabEstadoLibro(List<SabEstadoLibro> sabEstadoLibro) {
        this.sabEstadoLibro = sabEstadoLibro;
    }

    public HtmlInputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(HtmlInputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public HtmlCommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(HtmlCommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public HtmlCommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(HtmlCommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public HtmlCommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(HtmlCommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public HtmlCommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(HtmlCommandButton btnClear) {
        this.btnClear = btnClear;
    }

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

}
