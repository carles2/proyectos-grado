 
public class Jugador {
	private Casilla[] lasFichas= new Casilla[7];
	private String nombreJugador;
	private int puntuacion;
	
	
	Jugador(Bolsa bolsa){
		this ("Jugador",bolsa);
	}
	
	Jugador(String Nombre,Bolsa bolsa){
		nombreJugador=Nombre;
		puntuacion =0;
		setLasFichas(bolsa);
	}


	
	/**
	 * @return delvuelve las fichas del jugador para pintarlas
	 */
	public Casilla[] getLasFichas() {
		return lasFichas;
	}


	/**
	 * @param establece las fichas del jugador
	 * hay que hacerlo aleatorio tirando de un vector donde este el par letra-valor y segun el numero aleatorio cogerlo
	 */
	public void setLasFichas(Bolsa bolsa) {
		for (int i=0;i<7;i++){
			// no hace falta esta instancia de casilla ya que en la bolsa estan instanciadas
			// por lo tanto al igualar estamos enlazando con las fichas de la bolsa
			// lasFichas[i] = new Casilla();
			// ahora recore el vector de ficha y añade las que neceiste
			// para que esto funcione una vez sacadas del jugador han de ponerse a null
			if (lasFichas[i]==null)
				lasFichas[i]=bolsa.getFicha();
		}
	}

	/**
	 *  realiza las acciones de las fichas.
	 */
	public void realizaTurno(){
		
		// hay que hacer las cosas del turno mientras simulamos que sacamos todas las fichas.
		for(int i=0;i<7;i++)lasFichas[i]=null;
		
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
