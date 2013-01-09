/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
public class Casilla {
	private boolean vacio;
	private Ficha ficha;
	private boolean especial;
	private boolean primeraVez; // parece que no se usa
	private int posicionX;
	private int posicionY;
	private boolean validada;
	private Datos.TipoCasilla tCasilla;

	/**
	 * 
	 */
	public Casilla() {
		setVacio(true);
		setPrimeraVez(true);
		setEspecial(false);
		setValidada(false);
		setTCasilla(Datos.TipoCasilla.NORMAL);
	}

	/**
	 * @return el ficha
	 */
	public Ficha getFicha() {
		return ficha;
	}

	/**
	 * @return el posicionX
	 */
	public int getPosicionX() {
		return posicionX;
	}

	/**
	 * @return el posicionY
	 */
	public int getPosicionY() {
		return posicionY;
	}

	/**
	 * @return devuelve el tipo de casilla, NORMAL, DL, TL, DP, TP
	 */
	public Datos.TipoCasilla getTCasilla() {
		return tCasilla;
	}

	/**
	 * @return devuelve true si es una casilla especial
	 */
	public boolean isEspecial() {
		return especial;
	}

	/**
	 * @return devuelve true si es la primera vez que se evalua una casilla
	 */
	public boolean isPrimeraVez() {
		return primeraVez;
	}

	/**
	 * @return Devuelve true si la casilla esta vacia.
	 */
	public boolean isVacio() {
		return vacio;
	}

	/**
	 * Devuelve el valor de la casilla si esta validada o no, true esta
	 * validada, false no
	 * 
	 * @return true validada, false no validada
	 */
	public boolean isValidada() {
		return validada;
	}

	/**
	 * @param especial
	 * @param establece
	 *            si es una casilla especial
	 */
	public void setEspecial(boolean especial) {
		this.especial = especial;
	}

	/**
	 * @param ficha
	 *            el ficha a establecer
	 */
	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	/**
	 * @param posicionX
	 *            el posicionX a establecer
	 */
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * @param posicionY
	 *            el posicionY a establecer
	 */
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	/**
	 * @param primeraVez
	 * @param cambia
	 *            el valor si ya se ha evaluado una casilla
	 */
	public void setPrimeraVez(boolean primeraVez) {
		this.primeraVez = primeraVez;
	}

	/**
	 * @param tCasilla
	 * @param establece
	 *            el tipo de casilla, NORMAL, DL, TL, DP, TP
	 */
	public void setTCasilla(Datos.TipoCasilla tCasilla) {
		this.tCasilla = tCasilla;
	}

	/**
	 * @param vacio
	 * @param establece
	 *            el valor de la casilla
	 */
	public void setVacio(boolean vacio) {
		this.vacio = vacio;
	}

	/**
	 * Establece si la casilla ha sido validada o no en el diccionario
	 * 
	 * @param valor
	 *            true si esta validada, false si no
	 */
	public void setValidada(boolean valor) {
		this.validada = valor;
	}

}
