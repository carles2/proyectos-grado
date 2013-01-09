import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import javax.swing.ImageIcon;

/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
@SuppressWarnings("serial")
public class Tablero extends JPanel {

	private Casilla[][] elTablero;
	private Image fondoTablero;

	Tablero() {
		fondoTablero = new ImageIcon(Datos.FONDO_TABLERO).getImage();

		elTablero = new Casilla[15][];
		for (int i = 0; i < 15; i++) {
			// asigno en memoria para cada fila
			elTablero[i] = new Casilla[15];
			for (int j = 0; j < 15; j++) {
				// ahora creo un objeto para cada elemento
				elTablero[i][j] = new Casilla();
			}
		}
		inicializa();
	}

	/**
	 * 
	 * @param posx
	 * @param posy
	 * @param Coordenada
	 *            X
	 * @param Coordenada
	 *            Y
	 * @return Devuelve la casilla del tablero de las cordenadas X,Y
	 */
	public synchronized Casilla getCasilla(int posx, int posy) {
		return elTablero[posx][posy];
	}

	/**
	 * 
	 * @return devuelve el tablero de casillas actual
	 */
	public Casilla[][] getTablero() {
		return elTablero;
	}

	/**
	 * Inicializa la matriz con los valores de las casillas especiales, las del
	 * tipo (x,y,1) son de doble letra las del tipo (x,y,2) son de triple letra
	 * las del tipo (x,y,3) son de doble palabra las del tipo (x,y,4) son de
	 * triple palabra
	 */
	private void inicializa() {
		int matriz[][] = { { 2, 2, 1 }, { 12, 2, 1 }, { 6, 4, 1 }, { 8, 4, 1 },
				{ 4, 6, 1 }, { 10, 6, 1 }, { 4, 8, 1 }, { 10, 8, 1 },
				{ 6, 10, 1 }, { 8, 10, 1 }, { 2, 12, 1 }, { 12, 12, 1 },

				{ 4, 0, 2 }, { 10, 0, 2 }, { 1, 1, 2 }, { 13, 1, 2 },
				{ 6, 2, 2 }, { 8, 2, 2 }, { 3, 3, 2 }, { 11, 3, 2 },
				{ 0, 4, 2 }, { 14, 4, 2 }, { 5, 5, 2 }, { 9, 5, 2 },
				{ 2, 6, 2 }, { 12, 6, 2 }, { 2, 8, 2 }, { 12, 8, 2 },
				{ 5, 9, 2 }, { 9, 9, 2 }, { 0, 10, 2 }, { 14, 10, 2 },
				{ 3, 11, 2 }, { 11, 11, 2 }, { 6, 12, 2 }, { 8, 12, 2 },
				{ 1, 13, 2 }, { 13, 13, 2 }, { 4, 14, 2 }, { 10, 14, 2 },

				{ 5, 1, 3 }, { 9, 1, 3 }, { 7, 3, 3 }, { 1, 5, 3 },
				{ 13, 5, 3 }, { 1, 9, 3 }, { 13, 9, 3 }, { 7, 11, 3 },
				{ 5, 13, 3 }, { 9, 13, 3 },

				{ 0, 2, 4 }, { 0, 12, 4 }, { 2, 0, 4 }, { 2, 14, 4 },
				{ 12, 0, 4 }, { 12, 14, 4 }, { 14, 2, 4 }, { 14, 12, 4 } };
		for (int i = 0; i < matriz.length; i++) {
			// establecemos la casilla x,y del tipo especial
			elTablero[matriz[i][0]][matriz[i][1]].setEspecial(true);
			elTablero[matriz[i][0]][matriz[i][1]].setPosicionX(matriz[i][0]);
			elTablero[matriz[i][0]][matriz[i][1]].setPosicionY(matriz[i][1]);
			// segun el tercer parametro del vector establecemos el tipo de
			// casilla especial.
			switch (matriz[i][2]) {
			case 1:
				elTablero[matriz[i][0]][matriz[i][1]]
						.setTCasilla(Datos.TipoCasilla.DL);
				break;
			case 2:
				elTablero[matriz[i][0]][matriz[i][1]]
						.setTCasilla(Datos.TipoCasilla.TL);
				break;
			case 3:
				elTablero[matriz[i][0]][matriz[i][1]]
						.setTCasilla(Datos.TipoCasilla.DP);
				break;
			case 4:
				elTablero[matriz[i][0]][matriz[i][1]]
						.setTCasilla(Datos.TipoCasilla.TP);
				break;
			}

		}
		elTablero[7][7].setVacio(false);
		elTablero[7][7].setFicha(new Ficha('*', 0, -1, Datos.LETRA_COMODIN));
		elTablero[7][7].setPosicionX(7);
		elTablero[7][7].setPosicionY(7);
	}

	@Override
	public void paintComponent(Graphics g) {
		// Dibujar el fondo del formulario
		super.paintComponents(g);

		// Dibujar el tablero
		g.drawImage(fondoTablero, 0, 0, 526, 526, this);

		// Dibujar las fichas que se encuentran sobre el tablero
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (!elTablero[i][j].isVacio()) {
					g.drawImage(elTablero[i][j].getFicha().getImagen(), i * 35,
							j * 35, 35, 35, this);
				}
			}
		}
	}

	/**
	 * 
	 * @param casilla
	 * @param Guarda
	 *            una casilla en el tablero en la posicon de las coordenada de
	 *            casilla
	 */
	public synchronized void setCasilla(Casilla casilla) {
		int x, y;
		x = casilla.getPosicionX();
		y = casilla.getPosicionY();
		elTablero[x][y].setFicha(casilla.getFicha());
		elTablero[x][y].setPosicionX(x);
		elTablero[x][y].setPosicionY(y);
		elTablero[x][y].setVacio(casilla.isVacio());
		elTablero[x][y].setPrimeraVez(casilla.isPrimeraVez());

	}
}
