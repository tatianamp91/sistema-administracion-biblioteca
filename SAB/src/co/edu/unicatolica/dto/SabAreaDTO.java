package co.edu.unicatolica.dto;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import co.edu.presentacion.vista.SabAreaVista;
import co.edu.unicatolica.modelo.SabArea;

public class SabAreaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String estado;
    private String nombre;
    private String idArea;
    private boolean rowSelected = false;
    private SabAreaVista sabAreaView;
    private SabArea sabArea;

    public SabArea getSabArea() {
        return sabArea;
    }


    public void listener_cancel(ActionEvent e) {
        idArea = sabArea.getIdArea().toString();
        estado = (sabArea.getEstado() != null) ? sabArea.getEstado().toString()
                                               : null;
        nombre = (sabArea.getNombre() != null) ? sabArea.getNombre().toString()
                                               : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        idArea = sabArea.getIdArea().toString();
        estado = (sabArea.getEstado() != null) ? sabArea.getEstado().toString()
                                               : null;
        nombre = (sabArea.getNombre() != null) ? sabArea.getNombre().toString()
                                               : null;
        rowSelected = !rowSelected;
    }

    public void setSabArea(SabArea sabArea) {
        this.sabArea = sabArea;
    }

    public SabAreaVista getSabAreaView() {
        return sabAreaView;
    }

    public void setSabAreaView(SabAreaVista sabAreaView) {
        this.sabAreaView = sabAreaView;
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

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
