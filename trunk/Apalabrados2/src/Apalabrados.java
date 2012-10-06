import java.util.Random;

public class Apalabrados {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tablero tablero;
		Jugador jugador1,jugador2;
		Bolsa bolsa;
		Diccionario diccionario;
		Random r;
		
		diccionario = new Diccionario();
		r = new Random();
		bolsa = new Bolsa();

		tablero= new Tablero();
		jugador1 = new Jugador("Enrique",bolsa);
		jugador2 = new Jugador("Jose",bolsa);
		
		
		//realiza el juego mientras queden fichas
		tablero.pintaTablero();

		if (r.nextFloat() >=0.5)			
			do{
				jugador1.pintaFichas();
				jugador1.realizaTurno(tablero,bolsa,diccionario);
				// pinta la puntuacion
				System.out.println("puntuacion del jugador "+jugador1.getNombreJugador()+": "+jugador1.getPuntuacion()+" puntos");

				tablero.pintaTablero();
			
				jugador2.pintaFichas();
				jugador2.realizaTurno(tablero,bolsa,diccionario);

				// pinta la puntuacion
				System.out.println("puntuacion del jugador "+jugador2.getNombreJugador()+": "+jugador2.getPuntuacion()+" puntos");
			
				tablero.pintaTablero();
			}while (bolsa.getQuedanFichas());
		else
			do{
				jugador2.pintaFichas();
				jugador2.realizaTurno(tablero,bolsa,diccionario);
				// pinta la puntuacion
				System.out.println("puntuacion del jugador "+jugador2.getNombreJugador()+": "+jugador2.getPuntuacion()+" puntos");

				tablero.pintaTablero();
			
				jugador1.pintaFichas();
				jugador1.realizaTurno(tablero,bolsa,diccionario);

				// pinta la puntuacion
				System.out.println("puntuacion del jugador "+jugador1.getNombreJugador()+": "+jugador1.getPuntuacion()+" puntos");
			
				tablero.pintaTablero();
			}while (bolsa.getQuedanFichas());	
	}
}
