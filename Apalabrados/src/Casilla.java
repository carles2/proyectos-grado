  
public class Casilla {
	private char letra;
	private int valor;
	private boolean vacio;
	private boolean especial;
	private boolean primeraVez;
	private TipoCasilla tCasilla;
	
	public Casilla(){
		setVacio(true);
		setPrimeraVez(true);
		setEspecial(false);
		setTCasilla(TipoCasilla.NORMAL);
		/*
		vacio= true;
		especial=false;
		primeraVez=true;
		tCasilla=TipoCasilla.NORMAL;
		*/
	}
	
	/**
	 * @return el caracter contenido en "letra"
	 */
	public char getLetra() {
		return letra;
	}
	
	/**
	 * @param Establece el contendido de "letra" con letra pasada por parametro
	 */
	public void setLetra(char letra) {
		this.letra = letra;
	}
	/**
	 * @return el valor numerico de la casilla
	 */
	public int getValor() {
		return valor;
	}
	/**
	 * @param Establece el valor numerico de la casilla
	 */
	public void setValor(int valor) {
		this.valor = valor;
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
	public TipoCasilla getTCasilla() {
		return tCasilla;
	}
	/**
	 * @param establece  el tipo de casilla, NORMAL, DL, TL, DP, TP
	 */
	public void setTCasilla(TipoCasilla tCasilla) {
		this.tCasilla = tCasilla;
	}
	

}
