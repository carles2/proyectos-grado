  
public class Apalabrados {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tablero tablero;
		Jugador jugador1,jugador2;
		Bolsa bolsa;
		Diccionario diccionario;
		
		// hay que crear otra estrucctura hash para guardar las palabras que se van creando en el juego.
		
		bolsa = new Bolsa();
		diccionario = new Diccionario();
		tablero= new Tablero();
		jugador1 = new Jugador("Enrique",bolsa);
		jugador2 = new Jugador("Jose",bolsa);
		
		tablero.pintaTablero();
		jugador1.pintaFichas();
		jugador2.pintaFichas();
		
		System.out.println("numero de fichas restantes en la bolsa: "+ bolsa.getNumeroFichas());
		if (diccionario.EsValida("prueba")) System.out.print("Cadena encontrada");
		else System.out.print("Cadena no encontrada");
		

	}

}
