package co.edu.unicatolica.modelo;

public class SabLibroAutor implements java.io.Serializable {

	private static final long serialVersionUID = -1510174339681425271L;

	private SabLibroAutorId id;
	private SabLibro sabLibro;
	private SabAutor sabAutor;

	public SabLibroAutor() {
	}

	public SabLibroAutor(SabLibroAutorId id, SabLibro sabLibro,
			SabAutor sabAutor) {
		this.id = id;
		this.sabLibro = sabLibro;
		this.sabAutor = sabAutor;
	}

	public SabLibroAutorId getId() {
		return this.id;
	}

	public void setId(SabLibroAutorId id) {
		this.id = id;
	}

	public SabLibro getSabLibro() {
		return this.sabLibro;
	}

	public void setSabLibro(SabLibro sabLibro) {
		this.sabLibro = sabLibro;
	}

	public SabAutor getSabAutor() {
		return sabAutor;
	}

	public void setSabAutor(SabAutor sabAutor) {
		this.sabAutor = sabAutor;
	}

}
