package co.edu.unicatolica.modelo;

public class SabLibroAutorId implements java.io.Serializable {

	private static final long serialVersionUID = 3310010961436581217L;

	private Long idLibro;
	private Long idAutor;

	public SabLibroAutorId() {
	}

	public SabLibroAutorId(Long idLibro, Long idAutor) {
		this.idLibro = idLibro;
		this.idAutor = idAutor;
	}

	public Long getIdLibro() {
		return this.idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public Long getIdAutor() {
		return this.idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SabLibroAutorId))
			return false;
		SabLibroAutorId castOther = (SabLibroAutorId) other;

		return ((this.getIdLibro() == castOther.getIdLibro()) || (this
				.getIdLibro() != null && castOther.getIdLibro() != null && this
				.getIdLibro().equals(castOther.getIdLibro())))
				&& ((this.getIdAutor() == castOther.getIdAutor()) || (this
						.getIdAutor() != null && castOther.getIdAutor() != null && this
						.getIdAutor().equals(castOther.getIdAutor())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdLibro() == null ? 0 : this.getIdLibro().hashCode());
		result = 37 * result
				+ (getIdAutor() == null ? 0 : this.getIdAutor().hashCode());
		return result;
	}

}
