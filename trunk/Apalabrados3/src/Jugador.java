import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
@SuppressWarnings("serial")
public class Jugador extends JPanel {
	private Ficha[] lasFichas = new Ficha[7];
	private String nombreJugador;
	private int puntuacion;
	private int numeroJuegos;
	private boolean[] fichasNoUsadas = new boolean[7];
	private boolean turno = false;
	private Bolsa bolsa;

	private Image soporte;

	Jugador(Bolsa bolsa) {
		this("Jugador", bolsa);
	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param Nombre
	 *            del jugador
	 * @param bolsa
	 *            de letras
	 */
	Jugador(String Nombre, Bolsa bolsa) {
		nombreJugador = Nombre;
		numeroJuegos = 0;
		this.bolsa = bolsa;

		for (int i = 0; i < fichasNoUsadas.length; i++) {
			fichasNoUsadas[i] = false;
		}

		soporte = new ImageIcon(Datos.SOPORTE_FICHAS).getImage();

		puntuacion = 0; // Cada jugador comienza teniendo 0 puntos
		setLasFichas();
	}

	/**
	 * 
	 */
	public void devolverFichas() {
		for (int i = 0; i < 7; i++) {
			if (fichasNoUsadas[i] == true) {
				bolsa.DevolverFicha(lasFichas[i]);
				lasFichas[i] = bolsa.getFicha();
			}
		}
		repaint();
	}

	/**
	 * @param posicion
	 * @return
	 */
	public Ficha getFichaPosicion(int posicion) {
		fichasNoUsadas[posicion] = false;
		return lasFichas[posicion];
	}

	/**
	 * @return
	 */
	public boolean[] getFichasNoUsadas() {
		return fichasNoUsadas;
	}

	/**
	 * @return delvuelve las fichas del jugador para pintarlas
	 */
	public Ficha[] getLasFichas() {
		return lasFichas;
	}

	/**
	 * @return devuelve el nombre del jugador
	 */
	public String getNombreJugador() {
		return nombreJugador;
	}

	/**
	 * 
	 * @return delvuelve el numero de turnos pasados consecutivamente.
	 */
	public int getNumeroTurnosPasados() {
		return numeroJuegos;
	}

	/**
	 * @return devuelve la puntuacion del jugaor
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * 
	 */
	public void incrementaNumeroTurnosJugados() {
		numeroJuegos++;
	}

	/**
	 * @return
	 */
	public boolean isTurno() {
		return turno;
	}

	private void mensaje(String cadena) {
		JOptionPane.showMessageDialog(this, cadena);
	}

	@Override
	public void paintComponent(Graphics g) {
		// Dibujar el fondo del formulario
		super.paintComponents(g);

		// Dibujar el tablero
		g.drawImage(soporte, 0, 0, 369, 68, this);

		// Dibujar las fichas que se encuentran sobre el tablero
		int x = 15;
		for (int i = 0; i < 7; i++) {
			if (fichasNoUsadas[i]) {
				g.drawImage(lasFichas[i].getImagen(), x, 15, 35, 35, this);
			}
			x = x + 50;
		}
	}

	/**
	 * 
	 */
	public void realizaTurno() {
		int sumaLetras = 0;
		for (int i = 0; i < 7; i++) {
			if (fichasNoUsadas[i] == false) {
				sumaLetras++;
			}
		}
		if (sumaLetras == 7) {
			setPuntuacion(40); // si se han usado toda las letras un extra de 40
								// puntos
			mensaje("Felicidades has usado todas las letras tienes 40 puntos extras");
		}
		setLasFichas(); // pedimos nuevas fichas
		repaint();// pintamos de nuevo las fichas
	}

	/**
	 * @param posicion
	 */
	public void setFichaPosicion(int posicion) {
		fichasNoUsadas[posicion] = true;
	}

	/**
	 * @param establece
	 *            las fichas del jugador hay que hacerlo aleatorio tirando de un
	 *            vector donde este el par letra-valor y segun el numero
	 *            aleatorio cogerlo
	 */
	public void setLasFichas() {
		for (int i = 0; (i < 7) && (bolsa.getQuedanFichas()); i++) {
			// ahora recore el vector de ficha y aï¿½ade las que neceiste
			// para que esto funcione una vez sacadas del jugador han de ponerse
			// a null
			if (fichasNoUsadas[i] == false) {
				lasFichas[i] = bolsa.getFicha();
				fichasNoUsadas[i] = true;
			}
		}
	}

	/**
	 * Establece el nombre del jugador
	 * 
	 * @param nombreJugador
	 * 
	 * @param nombre
	 */
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	/**
	 * 
	 */
	public void setNumeroTurnosJugados0() {
		numeroJuegos = 0;
	}

	/**
	 * Establece la nueva puntuacion del jugador sumando a la anterior
	 * 
	 * @param puntuacion
	 *            , La puntuacion a sumar a la anterior.
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = this.puntuacion + puntuacion;
	}

	/**
	 * @param turno
	 */
	public void setTurno(boolean turno) {
		this.turno = turno;
	}
}