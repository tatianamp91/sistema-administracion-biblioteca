package co.edu.unicatolica.dto;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import co.edu.presentacion.vista.SabEdicionVista;
import co.edu.unicatolica.modelo.SabEdicion;

public class SabEdicionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descripcion;
    private String estado;
    private String idEdicion;
    private boolean rowSelected = false;
    private SabEdicionVista sabEdicionView;
    private SabEdicion sabEdicion;

    public SabEdicion getSabEdicion() {
        return sabEdicion;
    }

    public void listener_cancel(ActionEvent e) {
        idEdicion = sabEdicion.getIdEdicion().toString();
        descripcion = (sabEdicion.getDescripcion() != null)
            ? sabEdicion.getDescripcion().toString() : null;
        estado = (sabEdicion.getEstado() != null)
            ? sabEdicion.getEstado().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        idEdicion = sabEdicion.getIdEdicion().toString();
        descripcion = (sabEdicion.getDescripcion() != null)
            ? sabEdicion.getDescripcion().toString() : null;
        estado = (sabEdicion.getEstado() != null)
            ? sabEdicion.getEstado().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSabEdicion(SabEdicion sabEdicion) {
        this.sabEdicion = sabEdicion;
    }

    public SabEdicionVista getSabEdicionView() {
        return sabEdicionView;
    }

    public void setSabEdicionView(SabEdicionVista sabEdicionView) {
        this.sabEdicionView = sabEdicionView;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdEdicion() {
        return idEdicion;
    }

    public void setIdEdicion(String idEdicion) {
        this.idEdicion = idEdicion;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
