package co.edu.presentacion.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabEditorial;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabEditorialVista {
	
	private SabMensajesVista mensaje;
	
    private HtmlInputText txtEstado;
    private HtmlInputText txtNombre;
    private Long idEstado;
    private Long idEditorial;
    private HtmlCommandButton btnSave;
    private HtmlCommandButton btnModify;
    private HtmlCommandButton btnDelete;
    private HtmlCommandButton btnClear;
    private List<SelectItem> listEstado;

    private List<SabEditorial> sabEditorial;

    public SabEditorialVista() {
    	
    	mensaje = (SabMensajesVista) FacesUtils.getManagedBean("sabMensajesVista");
    	
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
	        DelegadoNegocioVista.saveSabEditorial((idEstado),
	        FacesUtils.checkString(txtNombre));
	        mensaje.addInfoMessage(FacesUtils.getMensaje("editorial.guardada"));	
            action_clear();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete() {
        try {
            DelegadoNegocioVista.deleteSabEditorial(idEditorial);
            mensaje.addInfoMessage(FacesUtils.getMensaje("editorial.eliminada"));
            action_clear();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    public String  action_commandLink(){
    	try{
    		SabEditorial sabEditoriall = DelegadoNegocioVista.getSabEditorial(idEditorial);
	   		if(sabEditoriall.getNombre() != null && sabEditoriall.getEstado() != 0L){
	   			txtNombre.setValue(sabEditoriall.getNombre());
	   			idEstado = sabEditoriall.getEstado();
	   			btnModify.setDisabled(false);
	   	        btnSave.setDisabled(true);
	   	        btnDelete.setDisabled(false);
	   	        btnModify.setDisabled(false);
	   	        btnClear.setDisabled(false);
    		}else{
    			throw new Exception(FacesUtils.getMensaje("error.editorial.no.encontrado"));
    		}
    	}catch (Exception e){
    		mensaje.addErrorMessage(e.getMessage());	
    	}return "";
 	}
    
    public String action_modify() {
        try {
            DelegadoNegocioVista.updateSabEditorial((idEstado), (idEditorial),
            FacesUtils.checkString(txtNombre));
            mensaje.addInfoMessage(FacesUtils.getMensaje("editorial.modificada"));
            action_clear();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public List<SabEditorial> getSabEditorial() {
        try {
            sabEditorial = DelegadoNegocioVista.getSabEditorial();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }
        return sabEditorial;
    }

    public void setSabEditorial(List<SabEditorial> sabEditorial) {
        this.sabEditorial = sabEditorial;
    }

    public HtmlInputText getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(HtmlInputText txtEstado) {
        this.txtEstado = txtEstado;
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

	public Long getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(Long idEditorial) {
		this.idEditorial = idEditorial;
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
