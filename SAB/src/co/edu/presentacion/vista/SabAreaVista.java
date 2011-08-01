package co.edu.presentacion.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabArea;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabAreaVista {

    private HtmlInputText txtNombre;
    private Long idEstado;
    private Long idArea;
    private HtmlCommandButton btnSave;
    private HtmlCommandButton btnModify;
    private HtmlCommandButton btnDelete;
    private HtmlCommandButton btnClear;
    private List<SelectItem> listEstado;

    private List<SabArea> sabArea;

    public SabAreaVista() {
    	txtNombre = new HtmlInputText();
    	btnSave = new HtmlCommandButton();
    	btnDelete = new HtmlCommandButton();
    	btnModify = new HtmlCommandButton();
    	btnClear = new HtmlCommandButton();
    	
    	listEstado = new ArrayList<SelectItem>();
    	listEstado.add(new SelectItem(0L, "Seleccione"));
    	listEstado.add(new SelectItem(1L, "Activo"));
    	listEstado.add(new SelectItem(2L, "Inactivo"));
    	
    	action_clear();
    }

    public String action_clear() {
    	idEstado = 0L;
        txtNombre.setValue(null);
        btnSave.setDisabled(false);
        btnDelete.setDisabled(true);
        btnModify.setDisabled(true);
        btnClear.setDisabled(false);

        return "";
    }

    public String action_save() {
        try {
            DelegadoNegocioVista.saveSabArea((idEstado), 
            FacesUtils.checkString(txtNombre));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("area.guardada"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
    }

    public String action_delete() {
        try {
            DelegadoNegocioVista.deleteSabArea (idArea);
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("area.eliminada"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
    }

    public String  action_commandLink(){
    	try{
   		SabArea sabAreaa = DelegadoNegocioVista.getSabArea(idArea);
   		if(sabAreaa.getNombre() != null && sabAreaa.getEstado() != 0L){
   			txtNombre.setValue(sabAreaa.getNombre());
   			idEstado = sabAreaa.getEstado();
   			btnModify.setDisabled(false);
   	        btnSave.setDisabled(true);
   	        btnDelete.setDisabled(false);
   	        btnModify.setDisabled(false);
   	        btnClear.setDisabled(false);
   			
    		}else{
    			throw new Exception("NO SE ENCONTRO EL AREA");
    		}
    	}catch (Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());	
    	}return "";
 	}
    
    public String action_modify() {
        try {

            DelegadoNegocioVista.updateSabArea((idEstado), (idArea),
            FacesUtils.checkString(txtNombre));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("area.modificada"));         
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public List<SabArea> getSabArea() {
            try {
                sabArea = DelegadoNegocioVista.getSabArea();
            } catch (Exception e) {
                FacesUtils.addErrorMessage(e.getMessage());
            }
            return sabArea;
        }


    public void setSabArea(List<SabArea> sabArea) {
        this.sabArea = sabArea;
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

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
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
