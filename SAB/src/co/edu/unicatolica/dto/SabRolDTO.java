package co.edu.unicatolica.dto;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import co.edu.presentacion.vista.SabRolVista;
import co.edu.unicatolica.modelo.SabRol;

public class SabRolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descripcion;
    private String idRol;
    private boolean rowSelected = false;
    private SabRolVista sabRolView;
    private SabRol sabRol;

    public SabRol getSabRol() {
        return sabRol;
    }

    public void listener_cancel(ActionEvent e) {
        idRol = sabRol.getIdRol().toString();
        descripcion = (sabRol.getDescripcion() != null)
            ? sabRol.getDescripcion().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        idRol = sabRol.getIdRol().toString();
        descripcion = (sabRol.getDescripcion() != null)
            ? sabRol.getDescripcion().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSabRol(SabRol sabRol) {
        this.sabRol = sabRol;
    }

    public SabRolVista getSabRolView() {
        return sabRolView;
    }

    public void setSabRolView(SabRolVista sabRolView) {
        this.sabRolView = sabRolView;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
