/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
@SuppressWarnings("rawtypes")
public class PalabrasIntroducidas implements Comparable {
	private String cadena;
	private int posicion;
	private boolean sentido;

	/**
	 * 
	 */
	public PalabrasIntroducidas() {

	}

	/**
	 * @param cadena
	 * @param posicion
	 * @param sentido
	 */
	public PalabrasIntroducidas(String cadena, int posicion, boolean sentido) {
		this.cadena = cadena;
		this.posicion = posicion;
		this.sentido = sentido;
	}

	@Override
	public int compareTo(Object o) {
		if (this == o) {
			return 0;
		}
		PalabrasIntroducidas other = (PalabrasIntroducidas) o;
		int salida;
		salida = this.getCadena().compareToIgnoreCase(other.getCadena());

		if (salida == 0) {
			if (this.getPosicion() == other.getPosicion()) {
				if (this.isSentido() == other.isSentido()) {
					return 0;
				} else {
					return Boolean.compare(this.isSentido(), other.isSentido());
				}
			} else {
				return Integer.compare(this.getPosicion(), other.getPosicion());
			}
		} else {
			return salida;
		}
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PalabrasIntroducidas)) {
			return false;
		}
		PalabrasIntroducidas other = (PalabrasIntroducidas) obj;
		if (cadena == null) {
			if (other.getCadena() != null) {
				return false;
			}
		} else if (!cadena.equals(other.getCadena())) {
			return false;
		}
		if (posicion != other.getPosicion()) {
			return false;
		}
		if (sentido != other.isSentido()) {
			return false;
		}
		return true;
	}

	/**
	 * @return el cadena
	 */
	public String getCadena() {
		return cadena;
	}

	/**
	 * @return el posicion
	 */
	public int getPosicion() {
		return posicion;
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadena == null) ? 0 : cadena.hashCode());
		result = prime * result + posicion;
		result = prime * result + (sentido ? 1231 : 1237);
		return result;
	}

	/**
	 * @return el sentido
	 */
	public boolean isSentido() {
		return sentido;
	}

	/**
	 * @param cadena
	 *            el cadena a establecer
	 */
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	/**
	 * @param posicion
	 *            el posicion a establecer
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	/**
	 * @param sentido
	 *            el sentido a establecer
	 */
	public void setSentido(boolean sentido) {
		this.sentido = sentido;
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PalabrasIntroducidas [cadena=" + cadena + ", posicion="
				+ posicion + ", sentido=" + sentido + "]";
	}

}
