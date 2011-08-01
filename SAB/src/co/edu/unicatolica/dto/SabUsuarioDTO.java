package co.edu.unicatolica.dto;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import co.edu.presentacion.vista.SabUsuarioVista;
import co.edu.unicatolica.modelo.SabUsuario;

public class SabUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String email;
    private String nombreCompleto;
    private String numIdentificacion;
    private String idRol_SabRol;
    private String idUsuario;
    private boolean rowSelected = false;
    private SabUsuarioVista sabUsuarioView;
    private SabUsuario sabUsuario;

    public SabUsuario getSabUsuario() {
        return sabUsuario;
    }

    public void listener_cancel(ActionEvent e) {
        idUsuario = sabUsuario.getIdUsuario().toString();
        codigo = (sabUsuario.getCodigo() != null)
            ? sabUsuario.getCodigo().toString() : null;
        email = (sabUsuario.getEmail() != null)
            ? sabUsuario.getEmail().toString() : null;
        nombreCompleto = (sabUsuario.getNombreCompleto() != null)
            ? sabUsuario.getNombreCompleto().toString() : null;
        numIdentificacion = (sabUsuario.getNumIdentificacion() != null)
            ? sabUsuario.getNumIdentificacion().toString() : null;
        idRol_SabRol = (sabUsuario.getSabRol().getIdRol() != null)
            ? sabUsuario.getSabRol().getIdRol().toString() : null;
        rowSelected = !rowSelected;
    }

    /**
    * <p>Bound to commandLink actionListener in the ui that renders/unrenders
        * the Customer details for editing.</p>
        */
    public void toggleSelected(ActionEvent e) {
        idUsuario = sabUsuario.getIdUsuario().toString();
        codigo = (sabUsuario.getCodigo() != null)
            ? sabUsuario.getCodigo().toString() : null;
        email = (sabUsuario.getEmail() != null)
            ? sabUsuario.getEmail().toString() : null;
        nombreCompleto = (sabUsuario.getNombreCompleto() != null)
            ? sabUsuario.getNombreCompleto().toString() : null;
        numIdentificacion = (sabUsuario.getNumIdentificacion() != null)
            ? sabUsuario.getNumIdentificacion().toString() : null;
        idRol_SabRol = (sabUsuario.getSabRol().getIdRol() != null)
            ? sabUsuario.getSabRol().getIdRol().toString() : null;
        rowSelected = !rowSelected;
    }

    public void setSabUsuario(SabUsuario sabUsuario) {
        this.sabUsuario = sabUsuario;
    }

    public SabUsuarioVista getSabUsuarioView() {
        return sabUsuarioView;
    }

    public void setSabUsuarioView(SabUsuarioVista sabUsuarioView) {
        this.sabUsuarioView = sabUsuarioView;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getIdRol_SabRol() {
        return idRol_SabRol;
    }

    public void setIdRol_SabRol(String idRol_SabRol) {
        this.idRol_SabRol = idRol_SabRol;
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
