package co.edu.presentacion.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabEdicion;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabEdicionVista {

	private SabMensajesVista mensaje;

	private HtmlInputText txtDescripcion;
	private Long idEstado;
	private Long idEdicion;
	private HtmlCommandButton btnSave;
	private HtmlCommandButton btnModify;
	private HtmlCommandButton btnDelete;
	private HtmlCommandButton btnClear;
	private List<SelectItem> listEstado;

	private List<SabEdicion> sabEdicion;

	public SabEdicionVista() {
		mensaje = (SabMensajesVista) FacesUtils.getManagedBean("sabMensajesVista");

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
			DelegadoNegocioVista.saveSabEdicion(
					FacesUtils.checkString(txtDescripcion), (idEstado));
			mensaje.addInfoMessage(FacesUtils.getMensaje("edicion.guardada"));
			action_clear();
		} catch (Exception e) {
			mensaje.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete() {
		try {
			DelegadoNegocioVista.deleteSabEdicion(idEdicion);
			mensaje.addInfoMessage(FacesUtils.getMensaje("edicion.eliminada"));
			action_clear();
		} catch (Exception e) {
			mensaje.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_commandLink() {
		try {
			SabEdicion sabEdicionn = DelegadoNegocioVista.getSabEdicion(idEdicion);
			if (sabEdicionn.getDescripcion() != null && sabEdicionn.getEstado() != 0L) {
				txtDescripcion.setValue(sabEdicionn.getDescripcion());
				idEstado = sabEdicionn.getEstado();
				btnModify.setDisabled(false);
				btnSave.setDisabled(true);
				btnDelete.setDisabled(false);
				btnModify.setDisabled(false);
				btnClear.setDisabled(false);
			} else {
				throw new Exception(FacesUtils.getMensaje("error.edicion.no.encontrado"));
			}
		} catch (Exception e) {
			mensaje.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_modify() {
		try {
			DelegadoNegocioVista.updateSabEdicion(
					FacesUtils.checkString(txtDescripcion), (idEstado),
					(idEdicion));
			mensaje.addInfoMessage(FacesUtils.getMensaje("edicion.modificada"));
			action_clear();
		} catch (Exception e) {
			mensaje.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public List<SabEdicion> getSabEdicion() {
		try {
			sabEdicion = DelegadoNegocioVista.getSabEdicion();
		} catch (Exception e) {
			mensaje.addErrorMessage(e.getMessage());
		}
		return sabEdicion;
	}

	public void setSabEdicion(List<SabEdicion> sabEdicion) {
		this.sabEdicion = sabEdicion;
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

	public Long getIdEdicion() {
		return idEdicion;
	}

	public void setIdEdicion(Long idEdicion) {
		this.idEdicion = idEdicion;
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
