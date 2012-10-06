public class Apalabrados {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tablero tablero;
		Jugador jugador1,jugador2;
		Bolsa bolsa;
		
		Diccionario diccionario;
		diccionario = new Diccionario();
	
		bolsa = new Bolsa();

		tablero= new Tablero();
		jugador1 = new Jugador("Enrique",bolsa);
		jugador2 = new Jugador("Jose",bolsa);
		
		//realiza el juego mientras queden fichas
		tablero.pintaTablero();
		do{
			jugador1.pintaFichas();
			jugador1.realizaTurno(tablero.getTablero(),bolsa,diccionario);
			// pinta la puntuacion
			System.out.println("puntuacion del jugador "+jugador1.getNombreJugador()+": "+jugador1.getPuntuacion()+" puntos");

			tablero.pintaTablero();
			
			jugador2.pintaFichas();
			jugador2.realizaTurno(tablero.getTablero(),bolsa,diccionario);

			// pinta la puntuacion
			System.out.println("puntuacion del jugador "+jugador2.getNombreJugador()+": "+jugador2.getPuntuacion()+" puntos");
			
			tablero.pintaTablero();
		}while (bolsa.getQuedanFichas());
	}
}
