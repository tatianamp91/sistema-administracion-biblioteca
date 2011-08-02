package co.edu.presentacion.delegadoNegocio;

import java.util.Date;
import java.util.List;

import co.edu.unicatolica.logica.SabAreaLogica;
import co.edu.unicatolica.logica.SabAutorLogica;
import co.edu.unicatolica.logica.SabEdicionLogica;
import co.edu.unicatolica.logica.SabEditorialLogica;
import co.edu.unicatolica.logica.SabEstadoLibroLogica;
import co.edu.unicatolica.logica.SabLibroLogica;
import co.edu.unicatolica.logica.SabPrestamoLogica;
import co.edu.unicatolica.logica.SabRolLogica;
import co.edu.unicatolica.logica.SabUsuarioLogica;
import co.edu.unicatolica.logica.SabVolumenLogica;
import co.edu.unicatolica.modelo.SabArea;
import co.edu.unicatolica.modelo.SabAutor;
import co.edu.unicatolica.modelo.SabEdicion;
import co.edu.unicatolica.modelo.SabEditorial;
import co.edu.unicatolica.modelo.SabEstadoLibro;
import co.edu.unicatolica.modelo.SabLibro;
import co.edu.unicatolica.modelo.SabPrestamo;
import co.edu.unicatolica.modelo.SabRol;
import co.edu.unicatolica.modelo.SabUsuario;
import co.edu.unicatolica.modelo.SabVolumen;

public class DelegadoNegocioVista {
    private DelegadoNegocioVista() {
    }

    public static List<SabArea> getSabArea() throws Exception {
        SabAreaLogica sabAreaLogic = new SabAreaLogica();
        return sabAreaLogic.getSabArea();
    }

    public static void saveSabArea(Long estado, String nombre)
        throws Exception {
        SabAreaLogica sabAreaLogic = new SabAreaLogica();
        sabAreaLogic.saveSabArea(estado, nombre);
    }

    public static void deleteSabArea(Long idArea) throws Exception {
        SabAreaLogica sabAreaLogic = new SabAreaLogica();
        sabAreaLogic.deleteSabArea(idArea);
    }

    public static void updateSabArea(Long estado, Long idArea, String nombre)
        throws Exception {
        SabAreaLogica sabAreaLogic = new SabAreaLogica();
        sabAreaLogic.updateSabArea(estado, idArea, nombre);
    }

    public static SabArea getSabArea(Long idArea) throws Exception {
        SabAreaLogica sabAreaLogic = new SabAreaLogica();
        SabArea sabArea = null;

        try {
            sabArea = sabAreaLogic.getSabArea(idArea);
        } catch (Exception e) {
            throw e;
        }

        return sabArea;
    }

    public static List<SabAutor> getSabAutor() throws Exception {
        SabAutorLogica sabAutorLogic = new SabAutorLogica();

        return sabAutorLogic.getSabAutor();
    }

    public static void saveSabAutor(String nombre)
        throws Exception {
        SabAutorLogica sabAutorLogic = new SabAutorLogica();
        sabAutorLogic.saveSabAutor(nombre);
    }

    public static void deleteSabAutor(Long idAutor) throws Exception {
        SabAutorLogica sabAutorLogic = new SabAutorLogica();
        sabAutorLogic.deleteSabAutor(idAutor);
    }

    public static void updateSabAutor(Long idAutor, String nombre)
        throws Exception {
        SabAutorLogica sabAutorLogic = new SabAutorLogica();
        sabAutorLogic.updateSabAutor(idAutor, nombre);
    }

    public static SabAutor getSabAutor(Long idAutor) throws Exception {
        SabAutorLogica sabAutorLogic = new SabAutorLogica();
        SabAutor sabAutor = null;

        try {
            sabAutor = sabAutorLogic.getSabAutor(idAutor);
        } catch (Exception e) {
            throw e;
        }

        return sabAutor;
    }

    public static List<SabEdicion> getSabEdicion() throws Exception {
        SabEdicionLogica sabEdicionLogic = new SabEdicionLogica();

        return sabEdicionLogic.getSabEdicion();
    }

    public static void saveSabEdicion(String descripcion, Long estado)
    	throws Exception {
        SabEdicionLogica sabEdicionLogic = new SabEdicionLogica();
        sabEdicionLogic.saveSabEdicion(descripcion, estado);
    }

    public static void deleteSabEdicion(Long idEdicion)
        throws Exception {
        SabEdicionLogica sabEdicionLogic = new SabEdicionLogica();
        sabEdicionLogic.deleteSabEdicion(idEdicion);
    }

    public static void updateSabEdicion(String descripcion, Long estado,
        Long idEdicion) throws Exception {
        SabEdicionLogica sabEdicionLogic = new SabEdicionLogica();
        sabEdicionLogic.updateSabEdicion(descripcion, estado, idEdicion);
    }

    public static SabEdicion getSabEdicion(Long idEdicion)
        throws Exception {
        SabEdicionLogica sabEdicionLogic = new SabEdicionLogica();
        SabEdicion sabEdicion = null;

        try {
            sabEdicion = sabEdicionLogic.getSabEdicion(idEdicion);
        } catch (Exception e) {
            throw e;
        }

        return sabEdicion;
    }

    public static List<SabEditorial> getSabEditorial()
        throws Exception {
        SabEditorialLogica sabEditorialLogic = new SabEditorialLogica();

        return sabEditorialLogic.getSabEditorial();
    }

    public static void saveSabEditorial(Long estado, String nombre)
    	throws Exception {
        SabEditorialLogica sabEditorialLogic = new SabEditorialLogica();
        sabEditorialLogic.saveSabEditorial(estado, nombre);
    }

    public static void deleteSabEditorial(Long idEditorial)
        throws Exception {
        SabEditorialLogica sabEditorialLogic = new SabEditorialLogica();
        sabEditorialLogic.deleteSabEditorial(idEditorial);
    }

    public static void updateSabEditorial(Long estado, Long idEditorial,
        String nombre) throws Exception {
        SabEditorialLogica sabEditorialLogic = new SabEditorialLogica();
        sabEditorialLogic.updateSabEditorial(estado, idEditorial, nombre);
    }

    public static SabEditorial getSabEditorial(Long idEditorial)
        throws Exception {
        SabEditorialLogica sabEditorialLogic = new SabEditorialLogica();
        SabEditorial sabEditorial = null;

        try {
            sabEditorial = sabEditorialLogic.getSabEditorial(idEditorial);
        } catch (Exception e) {
            throw e;
        }

        return sabEditorial;
    }

    public static List<SabEstadoLibro> getSabEstadoLibro()
        throws Exception {
        SabEstadoLibroLogica sabEstadoLibroLogic = new SabEstadoLibroLogica();

        return sabEstadoLibroLogic.getSabEstadoLibro();
    }

    public static void saveSabEstadoLibro(String descripcion)
        throws Exception {
        SabEstadoLibroLogica sabEstadoLibroLogic = new SabEstadoLibroLogica();
        sabEstadoLibroLogic.saveSabEstadoLibro(descripcion);
    }

    public static void deleteSabEstadoLibro(Long idEstado)
        throws Exception {
        SabEstadoLibroLogica sabEstadoLibroLogic = new SabEstadoLibroLogica();
        sabEstadoLibroLogic.deleteSabEstadoLibro(idEstado);
    }

    public static void updateSabEstadoLibro(String descripcion, Long idEstado)
        throws Exception {
        SabEstadoLibroLogica sabEstadoLibroLogic = new SabEstadoLibroLogica();
        sabEstadoLibroLogic.updateSabEstadoLibro(descripcion, idEstado);
    }

    public static SabEstadoLibro getSabEstadoLibro(Long idEstado)
        throws Exception {
        SabEstadoLibroLogica sabEstadoLibroLogic = new SabEstadoLibroLogica();
        SabEstadoLibro sabEstadoLibro = null;

        try {
            sabEstadoLibro = sabEstadoLibroLogic.getSabEstadoLibro(idEstado);
        } catch (Exception e) {
            throw e;
        }

        return sabEstadoLibro;
    }

    public static List<SabLibro> getSabLibro() throws Exception {
        SabLibroLogica sabLibroLogic = new SabLibroLogica();

        return sabLibroLogic.getSabLibro();
    }

    public static void saveSabLibro(Long cantidad, Long cantidadPrestados,
        String titulo, Long idArea_SabArea,
        Long idEdicion_SabEdicion, Long idEditorial_SabEditorial,
        Long idEstado_SabEstadoLibro, Long idVolumen_SabVolumen, List<SabAutor> listAutores)
        throws Exception {
        SabLibroLogica sabLibroLogic = new SabLibroLogica();
        sabLibroLogic.saveSabLibro(cantidad, cantidadPrestados,
            titulo, idArea_SabArea, idEdicion_SabEdicion,
            idEditorial_SabEditorial, idEstado_SabEstadoLibro,
            idVolumen_SabVolumen, listAutores);
    }

    public static void deleteSabLibro(Long idLibro) throws Exception {
        SabLibroLogica sabLibroLogic = new SabLibroLogica();
        sabLibroLogic.deleteSabLibro(idLibro);
    }

    public static void updateSabLibro(Long cantidad,
        Long idLibro, String titulo, Long idArea_SabArea,
        Long idEdicion_SabEdicion, Long idEditorial_SabEditorial,
        Long idVolumen_SabVolumen, List<SabAutor> listAutores)
        throws Exception {
        SabLibroLogica sabLibroLogic = new SabLibroLogica();
        sabLibroLogic.updateSabLibro(cantidad, idLibro,
            titulo, idArea_SabArea, idEdicion_SabEdicion,
            idEditorial_SabEditorial, idVolumen_SabVolumen, listAutores);
    }

    public static SabLibro getSabLibro(Long idLibro) throws Exception {
        SabLibroLogica sabLibroLogic = new SabLibroLogica();
        SabLibro sabLibro = null;

        try {
            sabLibro = sabLibroLogic.getSabLibro(idLibro);
        } catch (Exception e) {
            throw e;
        }

        return sabLibro;
    }

    public static List<SabPrestamo> getSabPrestamo() throws Exception {
        SabPrestamoLogica sabPrestamoLogic = new SabPrestamoLogica();

        return sabPrestamoLogic.getSabPrestamo();
    }

    public static void saveSabPrestamo(Long idLibro, Long idUsuario,
        Long estadoPrestamo, Date fechaDevolucion, Date fechaPrestamo,
        Date fechaRealDevolucion) throws Exception {
        SabPrestamoLogica sabPrestamoLogic = new SabPrestamoLogica();
        sabPrestamoLogic.saveSabPrestamo(idLibro, idUsuario, estadoPrestamo,
            fechaDevolucion, fechaPrestamo, fechaRealDevolucion);
    }

    public static  void devolverSabPrestamo(Long idPrestamo, Long estadoPrestamo)
    	throws Exception {
    	SabPrestamoLogica sabPrestamoLogic = new SabPrestamoLogica();
    	sabPrestamoLogic.devolverSabPrestamo(idPrestamo, estadoPrestamo);
    	
    }

    public static SabPrestamo getSabPrestamo(Long id)
        throws Exception {
        SabPrestamoLogica sabPrestamoLogic = new SabPrestamoLogica();
        SabPrestamo sabPrestamo = null;

        try {
            sabPrestamo = sabPrestamoLogic.getSabPrestamo(id);
        } catch (Exception e) {
            throw e;
        }

        return sabPrestamo;
    }

    public static List<SabPrestamo> buscarPorUsuarioLibro (Long idLibro, Long idUsuario) throws Exception {
    	try {
			SabPrestamoLogica sabPrestamoLogic = new SabPrestamoLogica();
			return sabPrestamoLogic.buscarPorUsuarioLibro(idLibro, idUsuario);
		} catch (Exception e) {
			throw e;
		}
    }

    public static List<SabRol> getSabRol() throws Exception {
        SabRolLogica sabRolLogic = new SabRolLogica();

        return sabRolLogic.getSabRol();
    }

    public static void saveSabRol(String descripcion)
        throws Exception {
        SabRolLogica sabRolLogic = new SabRolLogica();
        sabRolLogic.saveSabRol(descripcion);
    }

    public static void deleteSabRol(Long idRol) throws Exception {
        SabRolLogica sabRolLogic = new SabRolLogica();
        sabRolLogic.deleteSabRol(idRol);
    }

    public static void updateSabRol(String descripcion, Long idRol)
        throws Exception {
        SabRolLogica sabRolLogic = new SabRolLogica();
        sabRolLogic.updateSabRol(descripcion, idRol);
    }

    public static SabRol getSabRol(Long idRol) throws Exception {
        SabRolLogica sabRolLogic = new SabRolLogica();
        SabRol sabRol = null;

        try {
            sabRol = sabRolLogic.getSabRol(idRol);
        } catch (Exception e) {
            throw e;
        }

        return sabRol;
    }

    public static List<SabUsuario> getSabUsuario() throws Exception {
        SabUsuarioLogica sabUsuarioLogic = new SabUsuarioLogica();
        return sabUsuarioLogic.getSabUsuario();
    }
    
    public static SabUsuario consultarUsuarioPorCorreoCodigo(String correo, Long codigo) throws Exception {
        SabUsuarioLogica sabUsuarioLogic = new SabUsuarioLogica();
        return sabUsuarioLogic.consultarPorCorreoCodigo(correo, codigo);
    }
    
    public static SabUsuario consultarUsuarioPorCodigo(Long codigo) throws Exception {
        SabUsuarioLogica sabUsuarioLogic = new SabUsuarioLogica();
        return sabUsuarioLogic.consultarPorCodigo(codigo);
    }

    public static void saveSabUsuario(Long codigo, String email,
        String nombreCompleto, Long numIdentificacion,
        Long idRol_SabRol) throws Exception {
        SabUsuarioLogica sabUsuarioLogic = new SabUsuarioLogica();
        sabUsuarioLogic.saveSabUsuario(codigo, email, 
            nombreCompleto, numIdentificacion, idRol_SabRol);
    }

    public static void deleteSabUsuario(Long idUsuario)
        throws Exception {
        SabUsuarioLogica sabUsuarioLogic = new SabUsuarioLogica();
        sabUsuarioLogic.deleteSabUsuario(idUsuario);
    }

    public static void updateSabUsuario(Long codigo, String email,
        Long idUsuario, String nombreCompleto, Long numIdentificacion,
        Long idRol_SabRol) throws Exception {
        SabUsuarioLogica sabUsuarioLogic = new SabUsuarioLogica();
        sabUsuarioLogic.updateSabUsuario(codigo, email, idUsuario,
            nombreCompleto, numIdentificacion, idRol_SabRol);
    }

    public static SabUsuario getSabUsuario(Long idUsuario)
        throws Exception {
        SabUsuarioLogica sabUsuarioLogic = new SabUsuarioLogica();
        SabUsuario sabUsuario = null;

        try {
            sabUsuario = sabUsuarioLogic.getSabUsuario(idUsuario);
        } catch (Exception e) {
            throw e;
        }

        return sabUsuario;
    }

    public static List<SabVolumen> getSabVolumen() throws Exception {
        SabVolumenLogica sabVolumenLogic = new SabVolumenLogica();

        return sabVolumenLogic.getSabVolumen();
    }

    public static void saveSabVolumen(String descripcion, Long estado)
    	throws Exception {
        SabVolumenLogica sabVolumenLogic = new SabVolumenLogica();
        sabVolumenLogic.saveSabVolumen(descripcion, estado);
    }

    public static void deleteSabVolumen(Long idVolumen)
        throws Exception {
        SabVolumenLogica sabVolumenLogic = new SabVolumenLogica();
        sabVolumenLogic.deleteSabVolumen(idVolumen);
    }

    public static void updateSabVolumen(String descripcion, Long estado,
        Long idVolumen) throws Exception {
        SabVolumenLogica sabVolumenLogic = new SabVolumenLogica();
        sabVolumenLogic.updateSabVolumen(descripcion, estado, idVolumen);
    }

    public static SabVolumen getSabVolumen(Long idVolumen)
        throws Exception {
        SabVolumenLogica sabVolumenLogic = new SabVolumenLogica();
        SabVolumen sabVolumen = null;

        try {
            sabVolumen = sabVolumenLogic.getSabVolumen(idVolumen);
        } catch (Exception e) {
            throw e;
        }

        return sabVolumen;
    }

}
