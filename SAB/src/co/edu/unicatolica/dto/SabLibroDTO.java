package co.edu.unicatolica.dto;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import co.edu.presentacion.vista.SabLibroVista;
import co.edu.unicatolica.modelo.SabLibro;


public class SabLibroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cantidad;
    private String cantidadPrestados;
    private String titulo;
    private String idArea_SabArea;
    private String idEdicion_SabEdicion;
    private String idEditorial_SabEditorial;
    private String idEstado_SabEstadoLibro;
    private String idVolumen_SabVolumen;
    private String idLibro;
    private boolean rowSelected = false;
    private SabLibroVista sabLibroView;
    private SabLibro sabLibro;

    public SabLibro getSabLibro() {
        return sabLibro;
    }

    public void listener_cancel(ActionEvent e) {
        idLibro = sabLibro.getIdLibro().toString();
        cantidad = (sabLibro.getCantidad() != null)
            ? sabLibro.getCantidad().toString() : null;
        cantidadPrestados = (sabLibro.getCantidadPrestados() != null)
            ? sabLibro.getCantidadPrestados().toString() : null;
        titulo = (sabLibro.getTitulo() != null)
            ? sabLibro.getTitulo().toString() : null;
        idArea_SabArea = (sabLibro.getSabArea().getIdArea() != null)
            ? sabLibro.getSabArea().getIdArea().toString() : null;
        idEdicion_SabEdicion = (sabLibro.getSabEdicion().getIdEdicion() != null)
            ? sabLibro.getSabEdicion().getIdEdicion().toString() : null;
        idEditorial_SabEditorial = (sabLibro.getSabEditorial().getIdEditorial() != null)
            ? sabLibro.getSabEditorial().getIdEditorial().toString() : null;
        idEstado_SabEstadoLibro = (sabLibro.getSabEstadoLibro().getIdEstado() != null)
            ? sabLibro.getSabEstadoLibro().getIdEstado().toString() : null;
        idVolumen_SabVolumen = (sabLibro.getSabVolumen().getIdVolumen() != null)
            ? sabLibro.getSabVolumen().getIdVolumen().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        idLibro = sabLibro.getIdLibro().toString();
        cantidad = (sabLibro.getCantidad() != null)
            ? sabLibro.getCantidad().toString() : null;
        cantidadPrestados = (sabLibro.getCantidadPrestados() != null)
            ? sabLibro.getCantidadPrestados().toString() : null;
        titulo = (sabLibro.getTitulo() != null)
            ? sabLibro.getTitulo().toString() : null;
        idArea_SabArea = (sabLibro.getSabArea().getIdArea() != null)
            ? sabLibro.getSabArea().getIdArea().toString() : null;
        idEdicion_SabEdicion = (sabLibro.getSabEdicion().getIdEdicion() != null)
            ? sabLibro.getSabEdicion().getIdEdicion().toString() : null;
        idEditorial_SabEditorial = (sabLibro.getSabEditorial().getIdEditorial() != null)
            ? sabLibro.getSabEditorial().getIdEditorial().toString() : null;
        idEstado_SabEstadoLibro = (sabLibro.getSabEstadoLibro().getIdEstado() != null)
            ? sabLibro.getSabEstadoLibro().getIdEstado().toString() : null;
        idVolumen_SabVolumen = (sabLibro.getSabVolumen().getIdVolumen() != null)
            ? sabLibro.getSabVolumen().getIdVolumen().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSabLibro(SabLibro sabLibro) {
        this.sabLibro = sabLibro;
    }

    public SabLibroVista getSabLibroView() {
        return sabLibroView;
    }

    public void setSabLibroView(SabLibroVista sabLibroView) {
        this.sabLibroView = sabLibroView;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCantidadPrestados() {
        return cantidadPrestados;
    }

    public void setCantidadPrestados(String cantidadPrestados) {
        this.cantidadPrestados = cantidadPrestados;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdArea_SabArea() {
        return idArea_SabArea;
    }

    public void setIdArea_SabArea(String idArea_SabArea) {
        this.idArea_SabArea = idArea_SabArea;
    }

    public String getIdEdicion_SabEdicion() {
        return idEdicion_SabEdicion;
    }

    public void setIdEdicion_SabEdicion(String idEdicion_SabEdicion) {
        this.idEdicion_SabEdicion = idEdicion_SabEdicion;
    }

    public String getIdEditorial_SabEditorial() {
        return idEditorial_SabEditorial;
    }

    public void setIdEditorial_SabEditorial(String idEditorial_SabEditorial) {
        this.idEditorial_SabEditorial = idEditorial_SabEditorial;
    }

    public String getIdEstado_SabEstadoLibro() {
        return idEstado_SabEstadoLibro;
    }

    public void setIdEstado_SabEstadoLibro(String idEstado_SabEstadoLibro) {
        this.idEstado_SabEstadoLibro = idEstado_SabEstadoLibro;
    }

    public String getIdVolumen_SabVolumen() {
        return idVolumen_SabVolumen;
    }

    public void setIdVolumen_SabVolumen(String idVolumen_SabVolumen) {
        this.idVolumen_SabVolumen = idVolumen_SabVolumen;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
