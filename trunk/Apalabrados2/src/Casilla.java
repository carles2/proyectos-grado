  
public class Casilla {
	private boolean vacio;
	private Ficha ficha;
	private boolean especial;
	private boolean primeraVez; // parece que no se usa
	private int posicionX;
	private int posicionY;
	private Datos.TipoCasilla tCasilla;
	
	public Casilla(){
		setVacio(true);
		setPrimeraVez(true);
		setEspecial(false);
		setTCasilla(Datos.TipoCasilla.NORMAL);
		//ficha = new Ficha();
	}
	
	
	/**
	 * @return el posicionX
	 */
	public int getPosicionX() {
		return posicionX;
	}


	/**
	 * @param posicionX el posicionX a establecer
	 */
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}


	/**
	 * @return el posicionY
	 */
	public int getPosicionY() {
		return posicionY;
	}


	/**
	 * @param posicionY el posicionY a establecer
	 */
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	/**
	 * @return Devuelve true si la casilla esta vacia.
	 */
	public boolean isVacio() {
		return vacio;
	}
	/**
	 * @param establece el valor de la casilla
	 */
	public void setVacio(boolean vacio) {
		this.vacio = vacio;
	}
	/**
	 * @return devuelve true si es una casilla especial
	 */
	public boolean isEspecial() {
		return especial;
	}
	/**
	 * @param establece si es una casilla especial
	 */
	public void setEspecial(boolean especial) {
		this.especial = especial;
	}
	
	/**
	 * @return devuelve true si es la primera vez que se evalua una casilla
	 */
	public boolean isPrimeraVez() {
		return primeraVez;
	}

	/**
	 * @param cambia el valor si ya se ha evaluado una casilla
	 */
	public void setPrimeraVez(boolean primeraVez) {
		this.primeraVez = primeraVez;
	}
	
	/**
	 * @return devuelve el tipo de casilla, NORMAL, DL, TL, DP, TP
	 */
	public Datos.TipoCasilla getTCasilla() {
		return tCasilla;
	}
	
	/**
	 * @param establece  el tipo de casilla, NORMAL, DL, TL, DP, TP
	 */
	public void setTCasilla(Datos.TipoCasilla tCasilla) {
		this.tCasilla = tCasilla;
	}


	/**
	 * @return el ficha
	 */
	public Ficha getFicha() {
		return ficha;
	}


	/**
	 * @param ficha el ficha a establecer
	 */
	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}
	

}
