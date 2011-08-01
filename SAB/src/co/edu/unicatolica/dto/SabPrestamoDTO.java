package co.edu.unicatolica.dto;

import java.io.Serializable;
import java.util.Date;

import co.edu.presentacion.vista.SabPrestamoVista;
import co.edu.unicatolica.modelo.SabPrestamo;

public class SabPrestamoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String estadoPrestamo;
    private String idLibro_SabLibro;
    private String idUsuario_SabUsuario;
    private String idLibro;
    private String idUsuario;
    private Date fechaDevolucion;
    private Date fechaPrestamo;
    private Date fechaRealDevolucion;
    private boolean rowSelected = false;
    private SabPrestamoVista sabPrestamoView;
    private SabPrestamo sabPrestamo;

    public SabPrestamo getSabPrestamo() {
        return sabPrestamo;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void setSabPrestamo(SabPrestamo sabPrestamo) {
        this.sabPrestamo = sabPrestamo;
    }

    public SabPrestamoVista getSabPrestamoView() {
        return sabPrestamoView;
    }

    public void setSabPrestamoView(SabPrestamoVista sabPrestamoView) {
        this.sabPrestamoView = sabPrestamoView;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public String getIdLibro_SabLibro() {
        return idLibro_SabLibro;
    }

    public void setIdLibro_SabLibro(String idLibro_SabLibro) {
        this.idLibro_SabLibro = idLibro_SabLibro;
    }

    public String getIdUsuario_SabUsuario() {
        return idUsuario_SabUsuario;
    }

    public void setIdUsuario_SabUsuario(String idUsuario_SabUsuario) {
        this.idUsuario_SabUsuario = idUsuario_SabUsuario;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaRealDevolucion() {
        return fechaRealDevolucion;
    }

    public void setFechaRealDevolucion(Date fechaRealDevolucion) {
        this.fechaRealDevolucion = fechaRealDevolucion;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        this.rowSelected = rowSelected;
    }
}
