import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
public class FichasValidas {
	private ArrayList<Casilla> lista;
	private Tablero tablero;
	private Casilla casilla;

	/**
	 * @param tablero
	 */
	public FichasValidas(Tablero tablero) {
		this.tablero = tablero;
		lista = new ArrayList<Casilla>();
	}

	/**
	 * Si la casilla tiene vecinos la inserta en la lista sino no, ademas
	 * devuelve true si la ha insertado false si no la ha insertado por tanto no
	 * tiene vecinos
	 * 
	 * @param casilla
	 *            casilla a evaluar si es valida
	 * @return true si casilla valida, false si casilla no valida.
	 */
	public boolean add(Casilla casilla) {
		this.casilla = casilla;
		if (vecinos()) {
			comodin(casilla);
			lista.add(casilla);
			return true;
		}
		return false;
	}

	/**
	 * @param miCasilla
	 */
	public void comodin(Casilla miCasilla) {
		if (miCasilla.getFicha().getLetra() == '*') {
			boolean salida = false;
			String cadena;
			do {
				cadena = JOptionPane.showInputDialog(
						"Introduce el caracter del comodin", "").toUpperCase();
				if (Character.isLetter(cadena.charAt(0))) {
					salida = true;
				} else {
					JOptionPane.showMessageDialog(null,
							"Solo se aceptan letras");
				}
			} while (!salida);
			miCasilla.getFicha().setLetra(cadena.charAt(0));
		}
	}

	/**
	 * devuelve si la lista itnerna de ficha esta vacia o no
	 * 
	 * @return true si esta vacia false si no
	 */
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	/**
	 * elimina un elemento de la lista interna
	 * 
	 * @param posicion
	 *            el indice del elemento a borrar
	 * @return devuelve el elemento contenido en la posicion borrada
	 */
	public Casilla remove(int posicion) {
		return lista.remove(posicion);
	}

	/**
	 * Comprueba que una casilla tiene algun vecino, es decir ya exite una
	 * casilla previa. Si no hay casilla vecinas devuleve false y si hay vecinos
	 * devuelve true
	 * 
	 * @return true si vecinos, false si no vecinos.
	 */
	public boolean vecinos() {
		int x, y;
		boolean hayVecinos = false;
		x = casilla.getPosicionX();
		y = casilla.getPosicionY();

		if (((x > 0) && (x < 14)) && ((y > 0) && (y < 14))) {// esta en el
																// interior del
																// cuadro

			if (!tablero.getCasilla(x - 1, y).isVacio()) {
				comodin(tablero.getCasilla(x - 1, y));
				hayVecinos = true;
			}
			if (!tablero.getCasilla(x + 1, y).isVacio()) {
				comodin(tablero.getCasilla(x + 1, y));
				hayVecinos = true;
			}
			if (!tablero.getCasilla(x, y + 1).isVacio()) {
				comodin(tablero.getCasilla(x, y + 1));
				hayVecinos = true;
			}
			if (!tablero.getCasilla(x, y - 1).isVacio()) {
				comodin(tablero.getCasilla(x, y - 1));
				hayVecinos = true;
			}
		} else {// esta en el borde del cuadro
			if ((y == 0) || (y == 14)) {
				if ((x > 0) && (x < 14)) {
					if (tablero.getCasilla(x - 1, y).isVacio()) {
						comodin(tablero.getCasilla(x - 1, y));
						hayVecinos = true;
					}
					if (tablero.getCasilla(x + 1, y).isVacio()) {
						comodin(tablero.getCasilla(x + 1, y));
						hayVecinos = true;
					}
				} else {
					if (x == 0) {
						if (tablero.getCasilla(x + 1, y).isVacio()) {
							comodin(tablero.getCasilla(x + 1, y));
							hayVecinos = true;
						}
					} else {
						if (tablero.getCasilla(x - 1, y).isVacio()) {
							comodin(tablero.getCasilla(x - 1, y));
							hayVecinos = true;
						}
					}

				}
				if (y == 0) {
					if (tablero.getCasilla(x, y + 1).isVacio()) {
						comodin(tablero.getCasilla(x, y + 1));
						hayVecinos = true;
					}
				} else {
					if (tablero.getCasilla(x, y - 1).isVacio()) {
						comodin(tablero.getCasilla(x, y - 1));
						hayVecinos = true;
					}
				}
			}

			if ((x == 0) || (x == 14)) {
				if ((y > 0) && (y < 14)) {
					if (tablero.getCasilla(x, y - 1).isVacio()) {
						comodin(tablero.getCasilla(x, y - 1));
						hayVecinos = true;
					}
					if (tablero.getCasilla(x, y + 1).isVacio()) {
						comodin(tablero.getCasilla(x, y + 1));
						hayVecinos = true;
					}
				}

				if (x == 0) {
					if (tablero.getCasilla(x + 1, y).isVacio()) {
						comodin(tablero.getCasilla(x + 1, y));
						hayVecinos = true;
					}
				} else {
					if (tablero.getCasilla(x - 1, y).isVacio()) {
						comodin(tablero.getCasilla(x - 1, y));
						hayVecinos = true;
					}
				}
			}
		}
		return hayVecinos;

	}
}
