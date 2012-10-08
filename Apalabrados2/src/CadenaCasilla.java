public class CadenaCasilla {
	private Casilla casilla;
	private boolean jugador; // se pone a true cuando es una ficha del jugador no del tablero
	private int posicion;
	
	CadenaCasilla(){
	}

	
	/**
	 * @return el jugador
	 */
	public boolean isJugador() {
		return jugador;
	}

	/**
	 * @param jugador el jugador a establecer
	 */
	public void setJugador(boolean jugador) {
		this.jugador = jugador;
	}

	/**
	 * @return el posicion
	 */
	public int getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion el posicion a establecer
	 */
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	/**
	 * @param casilla el casilla a establecer
	 */
	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}
	
	/**
	 * 
	 * @return el casilla
	 */
	public Casilla getCasilla(){
		return casilla;
	}
}
