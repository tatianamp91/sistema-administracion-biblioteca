package co.edu.unicatolica.dto;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import co.edu.presentacion.vista.SabEditorialVista;
import co.edu.unicatolica.modelo.SabEditorial;


public class SabEditorialDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String estado;
    private String nombre;
    private String idEditorial;
    private boolean rowSelected = false;
    private SabEditorialVista sabEditorialView;
    private SabEditorial sabEditorial;

    public SabEditorial getSabEditorial() {
        return sabEditorial;
    }

    public void listener_cancel(ActionEvent e) {
        idEditorial = sabEditorial.getIdEditorial().toString();
        estado = (sabEditorial.getEstado() != null)
            ? sabEditorial.getEstado().toString() : null;
        nombre = (sabEditorial.getNombre() != null)
            ? sabEditorial.getNombre().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        idEditorial = sabEditorial.getIdEditorial().toString();
        estado = (sabEditorial.getEstado() != null)
            ? sabEditorial.getEstado().toString() : null;
        nombre = (sabEditorial.getNombre() != null)
            ? sabEditorial.getNombre().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSabEditorial(SabEditorial sabEditorial) {
        this.sabEditorial = sabEditorial;
    }

    public SabEditorialVista getSabEditorialView() {
        return sabEditorialView;
    }

    public void setSabEditorialView(SabEditorialVista sabEditorialView) {
        this.sabEditorialView = sabEditorialView;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(String idEditorial) {
        this.idEditorial = idEditorial;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
