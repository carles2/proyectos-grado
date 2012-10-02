//prueba
public class Apalabrados {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tablero tablero;
		Jugador jugador1,jugador2;
		Bolsa bolsa;
	
		bolsa = new Bolsa();

		tablero= new Tablero();
		jugador1 = new Jugador("Enrique",bolsa);
		jugador2 = new Jugador("Jose",bolsa);
		
		//realiza el juego mientras queden fichas
		do{
			jugador1.pintaFichas();
			jugador1.realizaTurno();
			jugador1.setLasFichas(bolsa);
			jugador1.setPuntuacion(tablero.busqueda());
			// pinta la puntuacion
			System.out.println("puntuacion del jugador "+jugador1.getNombreJugador()+": "+jugador1.getPuntuacion()+" puntos");
			///
			System.out.println("numero de fichas restantes en la bolsa: "+ bolsa.getNumeroFichas());
			///
			tablero.pintaTablero();
			jugador2.pintaFichas();
			jugador2.realizaTurno();
			jugador2.setLasFichas(bolsa);
			jugador2.setPuntuacion(tablero.busqueda());
			// pinta la puntuacion
			System.out.println("puntuacion del jugador "+jugador2.getNombreJugador()+": "+jugador2.getPuntuacion()+" puntos");
			///
			System.out.println("numero de fichas restantes en la bolsa: "+ bolsa.getNumeroFichas());
			///
			
			tablero.pintaTablero();
		}while (bolsa.getQuedanFichas());
	}
}
