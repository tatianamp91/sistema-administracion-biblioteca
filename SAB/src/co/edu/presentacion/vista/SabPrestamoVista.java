package co.edu.presentacion.vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.unicatolica.modelo.SabLibroAutor;
import co.edu.unicatolica.modelo.SabPrestamo;
import co.edu.unicatolica.modelo.SabUsuario;
import co.edu.utilities.FacesUtils;
import co.edu.utilities.Utilities;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;


public class SabPrestamoVista  {
	
	private SabMensajesVista mensaje;

    private Long estadoPrestamo;
    private HtmlInputText idLibro;
    private String nombreLibro;
    private String edicion;
    private String editorial;
    private String volumen;
    private Long cantidad;
    private Long cantidad_prestados;
    private String area;
    private HtmlInputText codigoUsuario;
    private Long idUsuario;
    private String nombreUsuario;
    private Long numId;
    private String email;
    private String rol;
    private SelectInputDate txtFechaDevolucion;
    private SelectInputDate txtFechaPrestamo;
    private SelectInputDate txtFechaRealDevolucion;
    private HtmlCommandButton btnSave;
    private HtmlCommandButton btnClearUsuario;
    private HtmlCommandButton btnClearLibro;
    private List<SabPrestamo> sabPrestamo;
    private List<SelectItem> listEstadoPrestamo;
    private List<SabLibroAutor> autores;

    public SabPrestamoVista() {
    	try {
        	mensaje = (SabMensajesVista) FacesUtils.getManagedBean("sabMensajesVista");

            txtFechaDevolucion = new SelectInputDate();
            txtFechaPrestamo =  new SelectInputDate();
            txtFechaRealDevolucion = new SelectInputDate();
            idLibro = new HtmlInputText();
            codigoUsuario = new HtmlInputText();
            btnSave = new HtmlCommandButton();
            btnClearUsuario = new HtmlCommandButton();
            btnClearLibro = new HtmlCommandButton();
            
            listEstadoPrestamo = new ArrayList<SelectItem>();
        	listEstadoPrestamo.add(new SelectItem(0L, "Seleccione"));
        	listEstadoPrestamo.add(new SelectItem(1L, "Activo"));
        	listEstadoPrestamo.add(new SelectItem(2L, "Inactivo"));

	    	action_clearUsuario();
	    	action_clearLibro();
	    	
		} catch (Exception e) {
			mensaje.addErrorMessage(e.getMessage());
		}
    }
    
    public void listener_txtIdLibro(ValueChangeEvent event){
    	try {
	    	if(event.getNewValue()!=  null && !event.getNewValue().equals(event.getOldValue())){
	    		if(!event.getNewValue().toString().isEmpty()){
	    			Long idLibro;
	    			if(Utilities.isNumeric(event.getNewValue().toString()) ){
	    				idLibro = Long.parseLong(event.getNewValue().toString());
	    			}else{
	    				throw new Exception(FacesUtils.getMensaje("error.idLibro.no.valido"));
	    			}
	    			
		    		SabLibro sabLibroo = DelegadoNegocioVista.getSabLibro(idLibro);
		    		if(sabLibroo!=null){
			    		nombreLibro = sabLibroo.getTitulo(); 
			    		edicion = sabLibroo.getSabEdicion().getDescripcion();
			    		editorial = sabLibroo.getSabEditorial().getNombre();
			    		volumen = sabLibroo.getSabVolumen().getDescripcion();
			    		cantidad = sabLibroo.getCantidad();
			    		cantidad_prestados = sabLibroo.getCantidadPrestados();
			    		area = sabLibroo.getSabArea().getNombre();
			    		
			    		sabPrestamo = DelegadoNegocioVista.buscarPorUsuarioLibro(idLibro, FacesUtils.checkLong(codigoUsuario));
			    		autores = new ArrayList<SabLibroAutor>();
			    		autores.addAll(sabLibroo.getSabLibroAutors());
			    		if(cantidad.equals(cantidad_prestados)){
			    			btnSave.setDisabled(true);
			    			throw new Exception(FacesUtils.getMensaje("prestamo.no.posible"));
			    		}else{
			    			btnSave.setDisabled(false);
			    		}
			    		
		    		}else{
		    			throw new Exception(FacesUtils.getMensaje("error.libro.no.encontrado"));
		    		}
	    		}else{
	    			action_clearLibro();
	    		}
	    	}
    	}catch (Exception e) {
    		action_clearLibro();
    		mensaje.addErrorMessage(e.getMessage());
		}
    }
    public void listener_txtCodigoUsuario(ValueChangeEvent event){
    	try {
	    	if(event.getNewValue()!=  null && !event.getNewValue().equals(event.getOldValue())){
	    		if(!event.getNewValue().toString().isEmpty()){
	    			Long codigoUsuario;
	    			if(Utilities.isNumeric(event.getNewValue().toString()) ){
	    				codigoUsuario = Long.parseLong(event.getNewValue().toString());
	    			}else{
	    				throw new Exception(FacesUtils.getMensaje("error.codigo.no.valido"));
	    			}
		    		SabUsuario sabUsuarioo = DelegadoNegocioVista.consultarUsuarioPorCodigo(codigoUsuario);				
		    		
		    		if(sabUsuarioo!=null){
		    			nombreUsuario = sabUsuarioo.getNombreCompleto(); 
			    		numId = sabUsuarioo.getNumIdentificacion();
			    		email = sabUsuarioo.getEmail();
			    		rol = sabUsuarioo.getSabRol().getDescripcion();
			    		idUsuario = sabUsuarioo.getIdUsuario();
			    		sabPrestamo = DelegadoNegocioVista.buscarPorUsuarioLibro(FacesUtils.checkLong(idLibro), codigoUsuario);
		    		}else{
		    			throw new Exception(FacesUtils.getMensaje("error.usuario.no.encontrado"));
		    		}	    		
		    	}else{
	    			action_clearUsuario();
		    	}
	    	}
    	}catch (Exception e) {
    		action_clearUsuario();
    		mensaje.addErrorMessage(e.getMessage());
		}
    }
  

    public String action_clearUsuario() {
    	try{
    		codigoUsuario.setValue(null);
	        nombreUsuario = null;
	        numId = null;
	        email = null;
	        rol = null;
        	sabPrestamo = new ArrayList<SabPrestamo>();
    	}catch (Exception e) {
    		mensaje.addErrorMessage(e.getMessage());
		}
        return "";
    }
    
    public String action_clear() {
    	action_clearUsuario();
    	action_clearLibro();
    	return "";
    }
    
    public String action_clearLibro() {
    	try{
	    	idLibro.setValue(null);
	        nombreLibro = null;
	        edicion = null;
	        editorial = null;
	        volumen = null;
	        cantidad = null;
	        cantidad_prestados = null;
	        area = null;
	        
	        Long codigoUsuario;
			if(this.codigoUsuario.getValue() != null && !this.codigoUsuario.getValue().toString().isEmpty()){
				if(Utilities.isNumeric(this.codigoUsuario.getValue().toString()) ){
					codigoUsuario = Long.parseLong(this.codigoUsuario.getValue().toString());
					if(codigoUsuario != null){
						sabPrestamo = DelegadoNegocioVista.buscarPorUsuarioLibro(null, codigoUsuario);
					}
				}else{
					throw new Exception(FacesUtils.getMensaje("error.codigo.no.valido"));
				}
			}
	        txtFechaDevolucion.setValue(null);
	        txtFechaDevolucion.setValue(null);
	        txtFechaPrestamo.setValue(null);
	        txtFechaRealDevolucion.setValue(null);
	        autores = new ArrayList<SabLibroAutor>();
    	}catch (Exception e) {
    		mensaje.addErrorMessage(e.getMessage());
		}
        return "";
    }


    public String action_save() {
        try {
        	estadoPrestamo = Long.parseLong(FacesUtils.getEtiqueta("estadoActivo"));
        	Date fechaActual = new Date();
        	if(fechaActual.before(FacesUtils.checkDate(txtFechaDevolucion))){
	        	DelegadoNegocioVista.saveSabPrestamo(FacesUtils.checkLong(idLibro),idUsuario,
	                (estadoPrestamo), FacesUtils.checkDate(txtFechaDevolucion), new Date(),
	                FacesUtils.checkDate(txtFechaRealDevolucion));
	        	mensaje.addInfoMessage(FacesUtils.getMensaje("prestamo.guardadoUno") + nombreLibro + " " + FacesUtils.getMensaje("prestamo.guardadoDos"));
	            action_clearUsuario();
		    	action_clearLibro();
        	}else{
        		throw new Exception(FacesUtils.getMensaje("prestamo.no.guardado"));
        	}
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    public String action_devolver() {
        try {
        	Long idPrestamo = Long.parseLong(FacesUtils.getRequestParameter("idPrestamo")) ;
        	estadoPrestamo = Long.parseLong(FacesUtils.getEtiqueta("estadoInactivo"));
        	DelegadoNegocioVista.devolverSabPrestamo(idPrestamo, estadoPrestamo);
        	mensaje.addInfoMessage(FacesUtils.getMensaje("prestamo.devuelto"));
            action_clearLibro();
            action_clearUsuario();
        } catch (Exception e) {
        	mensaje.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public SelectInputDate getTxtFechaDevolucion() {
        return txtFechaDevolucion;
    }

    public void setTxtFechaDevolucion(SelectInputDate txtFechaDevolucion) {
        this.txtFechaDevolucion = txtFechaDevolucion;
    }

    public SelectInputDate getTxtFechaPrestamo() {
        return txtFechaPrestamo;
    }

    public void setTxtFechaPrestamo(SelectInputDate txtFechaPrestamo) {
        this.txtFechaPrestamo = txtFechaPrestamo;
    }

    public SelectInputDate getTxtFechaRealDevolucion() {
        return txtFechaRealDevolucion;
    }

    public void setTxtFechaRealDevolucion(
        SelectInputDate txtFechaRealDevolucion) {
        this.txtFechaRealDevolucion = txtFechaRealDevolucion;
    }

    public HtmlCommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(HtmlCommandButton btnSave) {
        this.btnSave = btnSave;
    }


	public HtmlCommandButton getBtnClearUsuario() {
		return btnClearUsuario;
	}

	public void setBtnClearUsuario(HtmlCommandButton btnClearUsuario) {
		this.btnClearUsuario = btnClearUsuario;
	}

	public HtmlCommandButton getBtnClearLibro() {
		return btnClearLibro;
	}

	public void setBtnClearLibro(HtmlCommandButton btnClearLibro) {
		this.btnClearLibro = btnClearLibro;
	}

	public List<SabPrestamo> getSabPrestamo() {
		return sabPrestamo;
	}

	public void setSabPrestamo(List<SabPrestamo> sabPrestamo) {
		this.sabPrestamo = sabPrestamo;
	}

	public Long getEstadoPrestamo() {
		return estadoPrestamo;
	}

	public void setEstadoPrestamo(Long estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public List<SelectItem> getListEstadoPrestamo() {
		return listEstadoPrestamo;
	}

	public void setListEstadoPrestamo(List<SelectItem> listEstadoPrestamo) {
		this.listEstadoPrestamo = listEstadoPrestamo;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public HtmlInputText getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(HtmlInputText idLibro) {
		this.idLibro = idLibro;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public HtmlInputText getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(HtmlInputText codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Long getNumId() {
		return numId;
	}

	public void setNumId(Long numId) {
		this.numId = numId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getEdicion() {
		return edicion;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getVolumen() {
		return volumen;
	}

	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getCantidad_prestados() {
		return cantidad_prestados;
	}

	public void setCantidad_prestados(Long cantidad_prestados) {
		this.cantidad_prestados = cantidad_prestados;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<SabLibroAutor> getAutores() {
		return autores;
	}

	public void setAutores(List<SabLibroAutor> autores) {
		this.autores = autores;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
