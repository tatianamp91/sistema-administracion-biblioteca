package co.edu.presentacion.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabRol;
import co.edu.unicatolica.modelo.SabUsuario;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabUsuarioVista{
    private HtmlInputText txtCodigo;
    private HtmlInputText txtEmail;
    private HtmlInputText txtNombreCompleto;
    private HtmlInputText txtNumIdentificacion;
    private Long idRol;
    private Long idUsuario;
    private HtmlCommandButton btnSave;
    private HtmlCommandButton btnModify;
    private HtmlCommandButton btnDelete;
    private HtmlCommandButton btnClear;
    private List<SabUsuario> sabUsuario;
    private List<SelectItem> listRol;


    public SabUsuarioVista() {
    	try {
    	txtCodigo = new HtmlInputText();
    	txtEmail = new HtmlInputText();
    	txtNombreCompleto = new HtmlInputText();
    	txtNumIdentificacion = new HtmlInputText();
    	btnSave = new HtmlCommandButton();
    	btnDelete = new HtmlCommandButton();
    	btnModify = new HtmlCommandButton();
    	btnClear = new HtmlCommandButton();
    	
       	listRol = new ArrayList<SelectItem>();
		List<SabRol> roles = DelegadoNegocioVista.getSabRol();
       	listRol.add(new SelectItem(0L, "Seleccione"));
       	for (SabRol sabRol : roles) {
			listRol.add(new SelectItem(sabRol.getIdRol(), sabRol.getDescripcion()));
		}
     	action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    }

    public String action_clear() {
        txtCodigo.setValue(null);
        txtEmail.setValue(null);
        txtNombreCompleto.setValue(null);
        txtNumIdentificacion.setValue(null);
        idRol = 0L;
        btnSave.setDisabled(false);
        btnDelete.setDisabled(true);
        btnModify.setDisabled(true);
        btnClear.setDisabled(false);

        return "";
    }

  
    public String action_save() {
        try {
            DelegadoNegocioVista.saveSabUsuario(FacesUtils.checkLong(txtCodigo),
                FacesUtils.checkString(txtEmail),
                FacesUtils.checkString(txtNombreCompleto),
                FacesUtils.checkLong(txtNumIdentificacion), (idRol));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("usuario.guardado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete() {
        try {
            DelegadoNegocioVista.deleteSabUsuario(FacesUtils.checkLong(idUsuario));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("usuario.eliminado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    public String  action_commandLink(){
    	try{
   		SabUsuario sabUsuarioo = DelegadoNegocioVista.getSabUsuario(idUsuario);
   		if(sabUsuarioo.getIdUsuario() != null){
   			txtCodigo.setValue(sabUsuarioo.getCodigo());
   		    txtEmail.setValue(sabUsuarioo.getEmail());
   		    txtNombreCompleto.setValue(sabUsuarioo.getNombreCompleto());
   		    txtNumIdentificacion.setValue(sabUsuarioo.getNumIdentificacion());
 		    idRol = sabUsuarioo.getSabRol().getIdRol();
   			btnModify.setDisabled(false);
   	        btnSave.setDisabled(true);
   	        btnDelete.setDisabled(false);
   	        btnModify.setDisabled(false);
   	        btnClear.setDisabled(false);
   			
    		}else{
    			throw new Exception("NO SE ENCONTRO EL USUARIO");
    		}
    	}catch (Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());	
    	}return "";
 	}
    
    public String action_modify() {
        try {
            DelegadoNegocioVista.updateSabUsuario(FacesUtils.checkLong(
                    txtCodigo), FacesUtils.checkString(txtEmail), (idUsuario),
                FacesUtils.checkString(txtNombreCompleto),
                FacesUtils.checkLong(txtNumIdentificacion), (idRol));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("usuario.modificado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }


    public HtmlInputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(HtmlInputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public HtmlInputText getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(HtmlInputText txtEmail) {
        this.txtEmail = txtEmail;
    }

    public HtmlInputText getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    public void setTxtNombreCompleto(HtmlInputText txtNombreCompleto) {
        this.txtNombreCompleto = txtNombreCompleto;
    }

    public HtmlInputText getTxtNumIdentificacion() {
        return txtNumIdentificacion;
    }

    public void setTxtNumIdentificacion(HtmlInputText txtNumIdentificacion) {
        this.txtNumIdentificacion = txtNumIdentificacion;
    }

 
    public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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

	public List<SabUsuario> getSabUsuario() {
        try {
            sabUsuario = DelegadoNegocioVista.getSabUsuario();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
		return sabUsuario;
	}

	public void setSabUsuario(List<SabUsuario> sabUsuario) {
		this.sabUsuario = sabUsuario;
	}

	public List<SelectItem> getListRol() {
		return listRol;
	}

	public void setListRol(List<SelectItem> listRol) {
		this.listRol = listRol;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
    
}
