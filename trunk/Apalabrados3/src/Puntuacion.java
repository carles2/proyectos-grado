/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
public class Puntuacion {
	private int puntuacion;

	/**
	 * 
	 */
	public Puntuacion() {
		this(0);
	}

	/**
	 * @param puntuacion
	 */
	public Puntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * @return
	 */
	public synchronized int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * @param puntuacion
	 */
	public synchronized void sumaPuntuacion(int puntuacion) {
		this.puntuacion = this.puntuacion + puntuacion;
	}
}
