 
public class Jugador {
	private Casilla[] lasFichas= new Casilla[7];
	private String nombreJugador;
	private int puntuacion;
	
	
	Jugador(){
		this ("Jugador");
	}
	
	Jugador(String Nombre){
		nombreJugador=Nombre;
		puntuacion =0;
		setLasFichas();
	}


	
	/**
	 * @return delvuelve las fichas del jugador para pintarlas
	 */
	public Casilla[] getLasFichas() {
		return lasFichas;
	}


	/**
	 * @param establece las fichas del jugador
	 * hay que hacerlo aleatorio
	 */
	public void setLasFichas() {
		for (int i=0;i<7;i++){
			lasFichas[i] = new Casilla();
			lasFichas[i].setLetra((char) (i+65));
			lasFichas[i].setValor(i+1);
			lasFichas[i].setVacio(false);
		}
	}


	/**
	 * @return devuelve el nombre del jugador
	 */
	public String getNombreJugador() {
		return nombreJugador;
	}


	/**
	 * @param Establece el nombre del jugador
	 */
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}


	/**
	 * @return devuelve la puntuacion
	 */
	public int getPuntuacion() {
		return puntuacion;
	}


	/**
	 * @param establece la nueva puntuacion
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = this.puntuacion+ puntuacion;
	}
	
	public void pintaFichas(){
		
		System.out.println("Nombre del jugador: " +getNombreJugador());
		System.out.println("-----------------------------");
		for (int i=0; i<7; i++){
			System.out.print("| "+lasFichas[i].getLetra()+" ");
		}
		System.out.println("|");
		System.out.println("-----------------------------");
		for (int i=0; i<7; i++){
			System.out.print("| "+lasFichas[i].getValor()+" ");
		}
		System.out.println("|");
		System.out.println("-----------------------------");

	}

}
