package co.edu.presentacion.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import co.edu.presentacion.delegadoNegocio.DelegadoNegocioVista;
import co.edu.unicatolica.modelo.SabArea;
import co.edu.unicatolica.modelo.SabAutor;
import co.edu.unicatolica.modelo.SabEdicion;
import co.edu.unicatolica.modelo.SabEditorial;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.unicatolica.modelo.SabLibroAutor;
import co.edu.unicatolica.modelo.SabVolumen;
import co.edu.utilities.FacesUtils;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;

public class SabLibroVista  {
	private Long idLibro;
	private HtmlInputText txtTitulo;
	private Long idEdicion;
	private Long idVolumen;
	private Long idEditorial;
	private Long idArea;
	private Long idEstadoLibro;
	private HtmlInputText txtCantidad;
    private Long CantidadPrestados;   
    private HtmlCommandButton btnSave;
    private HtmlCommandButton btnModify;
    private HtmlCommandButton btnDelete;
    private HtmlCommandButton btnClear;
    private List<SabLibro> sabLibro;
    private List<SelectItem> listEdicion;
    private List<SelectItem> listVolumen;
    private List<SelectItem> listEditorial;
    private List<SelectItem> listArea;
    
    private List<SelectItem> listAutoresTotal;
    private List<SelectItem> listAutores;
    private List<String> listSelectAutores;
    private List<SelectItem> listAutoresLibro;
    private List<String> listSelectAutoresLibro;


	public SabLibroVista() {
		try {
	        txtTitulo = new HtmlInputText();
	        txtCantidad = new HtmlInputText();
	        btnSave = new HtmlCommandButton();
	        btnDelete = new HtmlCommandButton();
	        btnModify = new HtmlCommandButton();
	        btnClear = new HtmlCommandButton();
	        
	       	listEdicion = new ArrayList<SelectItem>();
			List<SabEdicion> ediciones = DelegadoNegocioVista.getSabEdicion();
	       	listEdicion.add(new SelectItem(0L, "Seleccione"));
	       	for (SabEdicion sabEdicion : ediciones) {
				listEdicion.add(new SelectItem(sabEdicion.getIdEdicion(), sabEdicion.getDescripcion()));
			}
	       	listVolumen = new ArrayList<SelectItem>();
	       	List<SabVolumen> volumenes = DelegadoNegocioVista.getSabVolumen();
	       	listVolumen.add(new SelectItem(0L,"Seleccione"));
	       	for(SabVolumen sabVolumen : volumenes){
	       		listVolumen.add(new SelectItem(sabVolumen.getIdVolumen(), sabVolumen.getDescripcion()));
	       	}
	       	listEditorial = new ArrayList<SelectItem>();
	       	List<SabEditorial> editoriales = DelegadoNegocioVista.getSabEditorial();
	       	listEditorial.add(new SelectItem (0L,"Seleccione"));
	       	for(SabEditorial sabEditorial : editoriales){
	       		listEditorial.add(new SelectItem(sabEditorial.getIdEditorial(), sabEditorial.getNombre()));
	       	}
	       	listArea = new ArrayList<SelectItem>();
	       	List<SabArea> areas = DelegadoNegocioVista.getSabArea();
	       	listArea.add(new SelectItem (0L,"Seleccione"));
	       	for(SabArea sabArea : areas){
	       		listArea.add(new SelectItem(sabArea.getIdArea(), sabArea.getNombre()));
	       	}
	       	
	       	listAutoresTotal = new ArrayList<SelectItem>();
	       	List<SabAutor> autores = DelegadoNegocioVista.getSabAutor();	       	
	       	for(SabAutor sabAutor : autores){
	       		listAutoresTotal.add(new SelectItem(sabAutor.getIdAutor(), sabAutor.getNombre()));
	       	}
	        action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}
    }

    public String action_clear() {
        idArea = 0L;
        idEdicion = 0L;
        idEditorial = 0L;
        idEstadoLibro = 0L;
        idVolumen = 0L;
        listAutores = new ArrayList<SelectItem>(listAutoresTotal);
        listAutoresLibro = new ArrayList<SelectItem>();
        listSelectAutores = new ArrayList<String>();
        listSelectAutoresLibro = new ArrayList<String>();
        txtTitulo.setValue(null);
        txtCantidad.setValue(null);
        btnSave.setDisabled(false);
        btnDelete.setDisabled(true);
        btnModify.setDisabled(true);
        btnClear.setDisabled(false);

        return "";
    }
    
    public SelectItem extraer_autor(String valor){
    	for (SelectItem selectItem : listAutoresTotal) {
			if(valor.equals(selectItem.getValue().toString())){
				return selectItem;
			}
		}
    	return null;
    }
    
    public String action_agregar_autor(){
    	for (String valor : listSelectAutores) {
    		SelectItem selectItem = extraer_autor(valor);
    		listAutoresLibro.add(selectItem);
    		listAutores.remove(selectItem);
		}
    	return "";
    }
    public String action_borrar_autor(){
    	for (String valor : listSelectAutoresLibro) {
    		SelectItem selectItem = extraer_autor(valor);
    		listAutores.add(selectItem);
    		listAutoresLibro.remove(selectItem);
		}
    	return "";
    }   
    public List<SabAutor> action_convertir(){
    	List<SabAutor> listAutor = new ArrayList<SabAutor>();
    	try {
	    	for (SelectItem element :listAutoresLibro) {
	    		SabAutor autor = DelegadoNegocioVista.getSabAutor(Long.parseLong(element.getValue().toString()));
	    		listAutor.add(autor);
			}
    	} catch (Exception e) {
    		FacesUtils.addErrorMessage(e.getMessage());
    		e.printStackTrace();
    	}
    	return listAutor;
    }
    public String action_save() {
        try { 
        	validaciones();
        	List<SabAutor> listAutor = action_convertir();
        	idEstadoLibro = 1L;
        	CantidadPrestados = 0L;
        	DelegadoNegocioVista.saveSabLibro(FacesUtils.checkLong(txtCantidad),
            (CantidadPrestados),FacesUtils.checkString(txtTitulo),
            (idArea),(idEdicion),(idEditorial),(idEstadoLibro),(idVolumen),(listAutor));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("libro.guardado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete() {
        try {
            DelegadoNegocioVista.deleteSabLibro(FacesUtils.checkLong(idLibro));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("libro.eliminado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    public String  action_commandLink(){
    	try{
   		SabLibro sabLibroo = DelegadoNegocioVista.getSabLibro(idLibro);
   		if(sabLibroo.getTitulo() != null){
   			txtTitulo.setValue(sabLibroo.getTitulo());
			idEstadoLibro = sabLibroo.getSabEstadoLibro().getIdEstado();
			idEdicion = sabLibroo.getSabEdicion().getIdEdicion();	        
   			idVolumen = sabLibroo.getSabVolumen().getIdVolumen();
 			idEditorial = sabLibroo.getSabEditorial().getIdEditorial(); 
   			idArea = sabLibroo.getSabArea().getIdArea();
   			
   			listAutores = new ArrayList<SelectItem>(listAutoresTotal);
   	        listAutoresLibro = new ArrayList<SelectItem>();
   			for (SabLibroAutor valor : sabLibroo.getSabLibroAutors()) {
	    		SelectItem selectItem = extraer_autor(valor.getSabAutor().getIdAutor().toString());
	    		listAutoresLibro.add(selectItem);
	    		listAutores.remove(selectItem);
			}
   			
   			txtCantidad.setValue(sabLibroo.getCantidad());		
   		    btnModify.setDisabled(false);
   	        btnSave.setDisabled(true);
   	        btnDelete.setDisabled(false);
   	        btnModify.setDisabled(false);
   	        btnClear.setDisabled(false);
   			
    		}else{
    			throw new Exception("NO SE ENCONTRO EL LIBRO");
    		}
    	}catch (Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());	
    	}return "";
 	}
    
    public void validaciones() throws Exception{
    	if(listAutoresLibro == null || listAutoresLibro.isEmpty()){
    		throw new Exception(FacesUtils.getMensaje("libro.no.autores"));
    	}
    }

    public String action_modify() {
        try {
        	validaciones();
        	List<SabAutor> listAutor = action_convertir();
            DelegadoNegocioVista.updateSabLibro(FacesUtils.checkLong(txtCantidad), 
            (idLibro), FacesUtils.checkString(txtTitulo), (idArea),
            (idEdicion), (idEditorial), (idVolumen), (listAutor));
            FacesUtils.addInfoMessage(FacesUtils.getMensaje("libro.modificado"));
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    
    public void setSabLibro(List<SabLibro> sabLibro) {
        this.sabLibro = sabLibro;
    }
	public List<SabLibro> getSabLibro() {
        try {
            sabLibro = DelegadoNegocioVista.getSabLibro();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
		return sabLibro;
	}

    public HtmlInputText getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(HtmlInputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public Long getCantidadPrestados() {
		return CantidadPrestados;
	}

	public void setCantidadPrestados(Long cantidadPrestados) {
		CantidadPrestados = cantidadPrestados;
	}

	public HtmlInputText getTxtTitulo() {
        return txtTitulo;
    }

    public void setTxtTitulo(HtmlInputText txtTitulo) {
        this.txtTitulo = txtTitulo;
    }

    public HtmlCommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(HtmlCommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public HtmlCommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(HtmlCommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public HtmlCommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(HtmlCommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public HtmlCommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(HtmlCommandButton btnClear) {
        this.btnClear = btnClear;
    }

	public Long getIdEdicion() {
		return idEdicion;
	}

	public void setIdEdicion(Long idEdicion) {
		this.idEdicion = idEdicion;
	}
    public List<SelectItem> getListEdicion() {
		return listEdicion;
	}

	public void setListEdicion(List<SelectItem> listEdicion) {
		this.listEdicion = listEdicion;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public Long getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(Long idEditorial) {
		this.idEditorial = idEditorial;
	}

	public Long getIdEstadoLibro() {
		return idEstadoLibro;
	}

	public void setIdEstadoLibro(Long idEstadoLibro) {
		this.idEstadoLibro = idEstadoLibro;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public Long getIdVolumen() {
		return idVolumen;
	}

	public void setIdVolumen(Long idVolumen) {
		this.idVolumen = idVolumen;
	}

	public List<SelectItem> getListVolumen() {
		return listVolumen;
	}

	public void setListVolumen(List<SelectItem> listVolumen) {
		this.listVolumen = listVolumen;
	}

	public List<SelectItem> getListEditorial() {
		return listEditorial;
	}

	public void setListEditorial(List<SelectItem> listEditorial) {
		this.listEditorial = listEditorial;
	}

	public List<SelectItem> getListArea() {
		return listArea;
	}

	public void setListArea(List<SelectItem> listArea) {
		this.listArea = listArea;
	}

	public List<SelectItem> getListAutores() {
		return listAutores;
	}

	public void setListAutores(List<SelectItem> listAutores) {
		this.listAutores = listAutores;
	}

	public List<String> getListSelectAutores() {
		return listSelectAutores;
	}

	public void setListSelectAutores(List<String> listSelectAutores) {
		this.listSelectAutores = listSelectAutores;
	}

	public List<SelectItem> getListAutoresLibro() {
		return listAutoresLibro;
	}

	public void setListAutoresLibro(List<SelectItem> listAutoresLibro) {
		this.listAutoresLibro = listAutoresLibro;
	}

	public List<String> getListSelectAutoresLibro() {
		return listSelectAutoresLibro;
	}

	public void setListSelectAutoresLibro(List<String> listSelectAutoresLibro) {
		this.listSelectAutoresLibro = listSelectAutoresLibro;
	}

	public List<SelectItem> getListAutoresTotal() {
		return listAutoresTotal;
	}

	public void setListAutoresTotal(List<SelectItem> listAutoresTotal) {
		this.listAutoresTotal = listAutoresTotal;
	}

}
