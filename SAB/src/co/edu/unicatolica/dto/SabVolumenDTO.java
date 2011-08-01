package co.edu.unicatolica.dto;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import co.edu.presentacion.vista.SabVolumenVista;
import co.edu.unicatolica.modelo.SabVolumen;

public class SabVolumenDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descripcion;
    private String estado;
    private String idVolumen;
    private boolean rowSelected = false;
    private SabVolumenVista sabVolumenView;
    private SabVolumen sabVolumen;

    public SabVolumen getSabVolumen() {
        return sabVolumen;
    }

    public void listener_cancel(ActionEvent e) {
        idVolumen = sabVolumen.getIdVolumen().toString();
        descripcion = (sabVolumen.getDescripcion() != null)
            ? sabVolumen.getDescripcion().toString() : null;
        estado = (sabVolumen.getEstado() != null)
            ? sabVolumen.getEstado().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        idVolumen = sabVolumen.getIdVolumen().toString();
        descripcion = (sabVolumen.getDescripcion() != null)
            ? sabVolumen.getDescripcion().toString() : null;
        estado = (sabVolumen.getEstado() != null)
            ? sabVolumen.getEstado().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSabVolumen(SabVolumen sabVolumen) {
        this.sabVolumen = sabVolumen;
    }

    public SabVolumenVista getSabVolumenView() {
        return sabVolumenView;
    }

    public void setSabVolumenView(SabVolumenVista sabVolumenView) {
        this.sabVolumenView = sabVolumenView;
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

    public String getIdVolumen() {
        return idVolumen;
    }

    public void setIdVolumen(String idVolumen) {
        this.idVolumen = idVolumen;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
