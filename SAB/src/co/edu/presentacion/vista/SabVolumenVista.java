package co.edu.presentacion.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabVolumen;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabVolumenVista {
    private HtmlInputText txtDescripcion;
    private Long idEstado;
    private Long idVolumen;
    private HtmlCommandButton btnSave;
    private HtmlCommandButton btnModify;
    private HtmlCommandButton btnDelete;
    private HtmlCommandButton btnClear;
    private List<SabVolumen> sabVolumen;
    private List<SelectItem> listEstado;

	public SabVolumenVista() {
    	txtDescripcion = new HtmlInputText();
    	btnSave = new HtmlCommandButton();
    	btnModify = new HtmlCommandButton();
    	btnDelete = new HtmlCommandButton();
    	btnClear = new HtmlCommandButton();
    	
    	listEstado = new ArrayList<SelectItem>();
    	listEstado.add(new SelectItem(0L, "Seleccione"));
    	listEstado.add(new SelectItem(1L, "Activo"));
    	listEstado.add(new SelectItem(2L, "Inactivo"));
    	
    	action_clear();
    }

    public String action_clear() {
        txtDescripcion.setValue(null);
        idEstado = 0L;
        btnSave.setDisabled(false);
        btnDelete.setDisabled(true);
        btnModify.setDisabled(true);
        btnClear.setDisabled(false);

        return "";
    }

    public String action_save() {
        try {
            DelegadoNegocioVista.saveSabVolumen(FacesUtils.checkString(
                    txtDescripcion), idEstado);
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("volumen.guardado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete() {
        try {
            DelegadoNegocioVista.deleteSabVolumen(idVolumen);
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("volumen.eliminado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String  action_commandLink(){
    	try{
   		SabVolumen sabVolumenn = DelegadoNegocioVista.getSabVolumen(idVolumen);
   		if(sabVolumenn.getDescripcion() != null && sabVolumenn.getEstado() != 0L){
   			txtDescripcion.setValue(sabVolumenn.getDescripcion());
   			idEstado = sabVolumenn.getEstado();
   			btnModify.setDisabled(false);
   	        btnSave.setDisabled(true);
   	        btnDelete.setDisabled(false);
   	        btnModify.setDisabled(false);
   	        btnClear.setDisabled(false);
   			
    		}else{
    			throw new Exception("NO SE ENCONTRO EL VOLUMEN");
    		}
    	}catch (Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());	
    	}return "";
 	}
    
    public String action_modify() {
        try {
            DelegadoNegocioVista.updateSabVolumen(FacesUtils.checkString(
                    txtDescripcion), idEstado, idVolumen);
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("volumen.modificado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
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

	public List<SabVolumen> getSabVolumen() {
		 try {
             sabVolumen = DelegadoNegocioVista.getSabVolumen();
         } catch (Exception e) {
             FacesUtils.addErrorMessage(e.getMessage());
         }
		return sabVolumen;
	}

	public void setSabVolumen(List<SabVolumen> sabVolumen) {
		this.sabVolumen = sabVolumen;
	}

	public Long getIdVolumen() {
		return idVolumen;
	}

	public void setIdVolumen(Long idVolumen) {
		this.idVolumen = idVolumen;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
	
    public List<SelectItem> getListEstado() {
		return listEstado;
	}

	public void setListEstado(List<SelectItem> listEstado) {
		this.listEstado = listEstado;
	}
}
