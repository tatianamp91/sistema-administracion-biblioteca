package co.edu.presentacion.vista;

import java.util.ArrayList;
import java.util.List;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabAutor;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabAutorVista {
    private HtmlInputText txtNombre;
    private Long idAutor;
    private HtmlCommandButton btnSave;
    private HtmlCommandButton btnModify;
    private HtmlCommandButton btnDelete;
    private HtmlCommandButton btnClear;
    
    List<SabAutor> sabAutor = new ArrayList<SabAutor>();

    public SabAutorVista() {
    	txtNombre = new HtmlInputText();
    	btnSave = new HtmlCommandButton();
    	btnModify = new HtmlCommandButton();
    	btnDelete = new HtmlCommandButton();
    	btnClear = new HtmlCommandButton();
    	
    	action_clear();

    }

    public String action_clear() {
        txtNombre.setValue(null);
        btnSave.setDisabled(false);
        btnDelete.setDisabled(true);
        btnModify.setDisabled(true);
        btnClear.setDisabled(false);

        return "";
    }
    
    public String action_save() {
        try {
            DelegadoNegocioVista.saveSabAutor(FacesUtils.checkString(txtNombre));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("autor.guardado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete() {
        try {
        	DelegadoNegocioVista.deleteSabAutor(idAutor);
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("autor.eliminado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    public String  action_commandLink(){
    	try{
   		SabAutor sabAutorr = DelegadoNegocioVista.getSabAutor(idAutor);
   		if(sabAutorr.getNombre() != null){
   			txtNombre.setValue(sabAutorr.getNombre());
   			btnModify.setDisabled(false);
   	        btnSave.setDisabled(true);
   	        btnDelete.setDisabled(false);
   	        btnModify.setDisabled(false);
   	        btnClear.setDisabled(false);
   			
    		}else{
    			throw new Exception("NO SE ENCONTRO EL AUTOR");
    		}
    	}catch (Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());	
    	}return "";
 	}
    
    public String action_modify() {
        try {
            DelegadoNegocioVista.updateSabAutor(idAutor, FacesUtils.checkString(txtNombre));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("autor.modificado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

  
    public List<SabAutor> getSabAutor() {
            try {
                sabAutor = DelegadoNegocioVista.getSabAutor();
            } catch (Exception e) {
                FacesUtils.addErrorMessage(e.getMessage());
            }

        return sabAutor;
    }

    public void setSabAutor(List<SabAutor> sabAutor) {
        this.sabAutor = sabAutor;
    }

    public HtmlInputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(HtmlInputText txtNombre) {
        this.txtNombre = txtNombre;
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

	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

}
