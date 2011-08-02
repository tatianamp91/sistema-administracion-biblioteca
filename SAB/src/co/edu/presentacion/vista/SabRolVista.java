package co.edu.presentacion.vista;

import java.util.List;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabRol;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabRolVista  {
	
	private SabMensajesVista mensaje;

    private HtmlInputText txtDescripcion;
    private Long idRol;
    private HtmlCommandButton btnSave;
    private HtmlCommandButton btnModify;
    private HtmlCommandButton btnDelete;
    private HtmlCommandButton btnClear;
    private List<SabRol> sabRol;

    public SabRolVista() {
    	
    	mensaje = (SabMensajesVista) FacesUtils.getManagedBean("sabMensajesVista");

    	txtDescripcion =  new HtmlInputText();
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
            DelegadoNegocioVista.saveSabRol(FacesUtils.checkString(
                    txtDescripcion));
            mensaje.addInfoMessage(FacesUtils.getMensaje("rol.guardado"));
            action_clear();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }
        return "";
    }

    public String action_delete() {
        try {
            DelegadoNegocioVista.deleteSabRol(idRol);
            mensaje.addInfoMessage(FacesUtils.getMensaje("rol.eliminado"));
            action_clear();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }
        return "";
    }
    
    public String  action_commandLink(){
    	try{
	   		SabRol sabRoll = DelegadoNegocioVista.getSabRol(idRol);
	   		if(sabRoll.getDescripcion() != null){
	   			txtDescripcion.setValue(sabRoll.getDescripcion());
	   			btnModify.setDisabled(false);
	   	        btnSave.setDisabled(true);
	   	        btnDelete.setDisabled(false);
	   	        btnModify.setDisabled(false);
	   	        btnClear.setDisabled(false);
    		}else{
    			throw new Exception(FacesUtils.getMensaje("error.rol.no.encontrado"));
    		}
    	}catch (Exception e){
    		mensaje.addErrorMessage(e.getMessage());	
    	}return "";
 	}
    
    public String action_modify() {
        try {
            DelegadoNegocioVista.updateSabRol(FacesUtils.checkString(
                    txtDescripcion), idRol);
            mensaje.addInfoMessage(FacesUtils.getMensaje("rol.modificado"));
            action_clear();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }
        return "";
    }

    public List<SabRol> getSabRol() {
        try {
            sabRol = DelegadoNegocioVista.getSabRol();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }
        return sabRol;
    }

    public void setSabRol(List<SabRol> sabRol) {
        this.sabRol = sabRol;
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

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

}
