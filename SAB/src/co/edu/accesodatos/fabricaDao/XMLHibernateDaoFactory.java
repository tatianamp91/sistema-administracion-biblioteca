package co.edu.accesodatos.fabricaDao;

import co.edu.accesodatos.dao.*;

public class XMLHibernateDaoFactory {
	private static XMLHibernateDaoFactory instance = null;

	private XMLHibernateDaoFactory() {
	}

	public static XMLHibernateDaoFactory getInstance() {
		if (instance == null) {
			instance = new XMLHibernateDaoFactory();
		}

		return instance;
	}

	public SabAreaDAO getSabAreaDAO() {
		return new SabAreaDAO();
	}

	public SabAutorDAO getSabAutorDAO() {
		return new SabAutorDAO();
	}

	public SabEdicionDAO getSabEdicionDAO() {
		return new SabEdicionDAO();
	}

	public SabEditorialDAO getSabEditorialDAO() {
		return new SabEditorialDAO();
	}

	public SabEstadoLibroDAO getSabEstadoLibroDAO() {
		return new SabEstadoLibroDAO();
	}

	public SabLibroDAO getSabLibroDAO() {
		return new SabLibroDAO();
	}

	public SabPrestamoDAO getSabPrestamoDAO() {
		return new SabPrestamoDAO();
	}

	public SabRolDAO getSabRolDAO() {
		return new SabRolDAO();
	}

	public SabUsuarioDAO getSabUsuarioDAO() {
		return new SabUsuarioDAO();
	}

	public SabVolumenDAO getSabVolumenDAO() {
		return new SabVolumenDAO();
	}

	public SabLibroAutorDAO getSabLibroAutorDAO() {
		return new SabLibroAutorDAO();
	}
}
