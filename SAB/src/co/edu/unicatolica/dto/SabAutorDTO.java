package co.edu.unicatolica.dto;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import co.edu.presentacion.vista.SabAutorVista;
import co.edu.unicatolica.modelo.SabAutor;

public class SabAutorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String idAutor;
    private boolean rowSelected = false;
    private SabAutorVista sabAutorView;
    private SabAutor sabAutor;

    public SabAutor getSabAutor() {
        return sabAutor;
    }

    public void listener_cancel(ActionEvent e) {
        idAutor = sabAutor.getIdAutor().toString();
        nombre = (sabAutor.getNombre() != null)
            ? sabAutor.getNombre().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        idAutor = sabAutor.getIdAutor().toString();
        nombre = (sabAutor.getNombre() != null)
            ? sabAutor.getNombre().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSabAutor(SabAutor sabAutor) {
        this.sabAutor = sabAutor;
    }

    public SabAutorVista getSabAutorView() {
        return sabAutorView;
    }

    public void setSabAutorView(SabAutorVista sabAutorView) {
        this.sabAutorView = sabAutorView;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
