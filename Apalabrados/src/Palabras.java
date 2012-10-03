/**
 * Clase para guardar las palabras hereda de comparable, los metodos los ha generado solo el ecplipse
 * se ha redefinido el metodo compareTo para las ordenaciones.
 * @author Alkimi
 *
 */
public class Palabras implements Comparable<Object> {
	private String cadena;
	private int posicion;
	private boolean direccion;
	
	public Palabras(){}
	
	public Palabras(String cadena, int posicion, boolean direccion){
		this.cadena=cadena;
		this.posicion=posicion;
		this.direccion=direccion;
	}
	/* (sin Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadena == null) ? 0 : cadena.hashCode());
		result = prime * result + (direccion ? 1231 : 1237);
		result = prime * result + posicion;
		return result;
	}
	/* (sin Javadoc)
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
		if (!(obj instanceof Palabras)) {
			return false;
		}
		Palabras other = (Palabras) obj;
		if (cadena == null) {
			if (other.cadena != null) {
				return false;
			}
		} else if (!cadena.equals(other.cadena)) {
			return false;
		}
		if (direccion != other.direccion) {
			return false;
		}
		if (posicion != other.posicion) {
			return false;
		}
		return true;
	}
	/* (sin Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Palabras [cadena=" + cadena + ", posicion=" + posicion
				+ ", direccion=" + direccion + "]";
	}
	@Override
	public int compareTo(Object o) {
		Palabras palabras = (Palabras) o;
		int comparadorCadena = cadena.compareToIgnoreCase(palabras.cadena);
		int comparadorPosicion = Integer.compare(posicion, palabras.posicion);
		int comparadorDireccion= Boolean.compare(direccion, palabras.direccion);
		return (comparadorCadena!=0?comparadorCadena:comparadorPosicion!=0?comparadorPosicion:comparadorDireccion);
	}

	/**
	 * @param cadena el cadena a establecer
	 */
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	/**
	 * @param posicion el posicion a establecer
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	/**
	 * @param direccion el direccion a establecer
	 */
	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
	}

}