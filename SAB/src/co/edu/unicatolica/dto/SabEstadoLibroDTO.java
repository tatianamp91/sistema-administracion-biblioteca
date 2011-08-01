package co.edu.unicatolica.dto;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import co.edu.presentacion.vista.SabEstadoLibroVista;
import co.edu.unicatolica.modelo.SabEstadoLibro;

public class SabEstadoLibroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descripcion;
    private String idEstado;
    private boolean rowSelected = false;
    private SabEstadoLibroVista sabEstadoLibroView;
    private SabEstadoLibro sabEstadoLibro;

    public SabEstadoLibro getSabEstadoLibro() {
        return sabEstadoLibro;
    }

    public void listener_cancel(ActionEvent e) {
        idEstado = sabEstadoLibro.getIdEstado().toString();
        descripcion = (sabEstadoLibro.getDescripcion() != null)
            ? sabEstadoLibro.getDescripcion().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        idEstado = sabEstadoLibro.getIdEstado().toString();
        descripcion = (sabEstadoLibro.getDescripcion() != null)
            ? sabEstadoLibro.getDescripcion().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSabEstadoLibro(SabEstadoLibro sabEstadoLibro) {
        this.sabEstadoLibro = sabEstadoLibro;
    }

    public SabEstadoLibroVista getSabEstadoLibroView() {
        return sabEstadoLibroView;
    }

    public void setSabEstadoLibroView(SabEstadoLibroVista sabEstadoLibroView) {
        this.sabEstadoLibroView = sabEstadoLibroView;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
