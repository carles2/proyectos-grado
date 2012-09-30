  
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
		
		tablero.pintaTablero();
		jugador1.pintaFichas();
		jugador2.pintaFichas();
		
		//zona de pruebas
		
		System.out.println("numero de fichas restantes en la bolsa: "+ bolsa.getNumeroFichas());

		
		//fin zona de pruebas
	}

}
