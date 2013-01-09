import java.util.Random;

/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
public class Bolsa {

	private int numeroFichas;
	private Random r;
	private Ficha[] miBolsa;
	private boolean[] usado;

	Bolsa() {

		/**
		 * distribucion de letras segun el scrabbel original, a modificar segun
		 * la distribucion de apalabrados 0 puntos: dos comodines 1 punto: A*12,
		 * E*12, I*6, L*4, N*5, O*9, R*5, S*6, T*4, U*5, 2 puntos: D*5, G*2 3
		 * puntos: B*2, C*4, M*2, P*2 4 puntos: F*1, H*2, V*1, Y*1 5 putnos: Q*1
		 * 8 puntos: J*1, X*1 10 puntos: Ñ*1, Z*1 no esta la K ni la W, pero se
		 * podrian añadir a la de 10 puntos
		 */

		Object abecedario[][] = {

		{ 65, 1, 12, Datos.LETRA_A }, // Letra A, Valor de A, numero de A,
										// imagen de la ficha de A
				{ 66, 3, 2, Datos.LETRA_B }, // Letra B
				{ 67, 3, 4, Datos.LETRA_C }, // Letra C
				{ 68, 2, 5, Datos.LETRA_D }, // Letra D
				{ 69, 1, 12, Datos.LETRA_E }, // Letra E
				{ 70, 4, 1, Datos.LETRA_F }, // Letra F
				{ 71, 2, 2, Datos.LETRA_G }, // Letra G
				{ 72, 4, 2, Datos.LETRA_H }, // Letra H
				{ 73, 1, 6, Datos.LETRA_I }, // Letra I
				{ 74, 8, 1, Datos.LETRA_J }, // Letra J
				// {75,5,1, Datos.Letra_K}, // Letra K
				{ 76, 1, 4, Datos.LETRA_L }, // Letra L
				{ 77, 3, 2, Datos.LETRA_M }, // Letra M
				{ 78, 1, 5, Datos.LETRA_N }, // Letra N
				{ 209, 10, 1, Datos.LETRA_ENIE }, // Letra Ñ
				{ 79, 1, 9, Datos.LETRA_O }, // Letra O
				{ 80, 3, 2, Datos.LETRA_P }, // Letra P
				{ 81, 5, 1, Datos.LETRA_Q }, // Letra Q
				{ 82, 1, 5, Datos.LETRA_R }, // Letra R
				{ 83, 1, 6, Datos.LETRA_S }, // Letra S
				{ 84, 1, 4, Datos.LETRA_T }, // Letra T
				{ 85, 1, 5, Datos.LETRA_U }, // Letra U
				{ 86, 4, 1, Datos.LETRA_V }, // Letra V
				// {87, 10,1, Datos.LETRA_W}, // Letra W
				{ 88, 8, 1, Datos.LETRA_X }, // Letra X
				{ 89, 4, 1, Datos.LETRA_Y }, // Letra Y
				{ 90, 10, 1, Datos.LETRA_Z }, // Letra Z
				{ 42, 0, Datos.NUMERO_COMODIN, Datos.LETRA_COMODIN } // Comodin
																		// *
		};

		usado = new boolean[Datos.NUMERO_FICHAS];
		numeroFichas = Datos.NUMERO_FICHAS;
		miBolsa = new Ficha[Datos.NUMERO_FICHAS];

		int identificador = 0;

		for (int i = 0; i < Datos.NUMERO_FICHAS; i++) {
			usado[i] = false;
		}

		for (int i = 0; i < abecedario.length; i++) {
			for (int j = 0; j < (int) abecedario[i][2]; j++) {
				miBolsa[identificador] = new Ficha(
						(char) (int) abecedario[i][0], (int) abecedario[i][1],
						identificador, (String) abecedario[i][3]);
				identificador++;
			}
		}

	}

	/**
	 * Devolvemos una ficha a la bolsa
	 * 
	 * @param ficha
	 *            la ficha a devolver
	 */
	public void DevolverFicha(Ficha ficha) {
		// para devolver una ficha a la bolsa solo hay que cambiar su variable
		// usado
		// de tal manera que se pueda devolver de nuevo e incrementar el numero
		// de fichas totales
		usado[ficha.getIdentificador()] = false;
		numeroFichas++;
	}

	/**
	 * sacamos una ficha de forma aleatoria de la bolsa y cuando la sacamos la
	 * marcamos con el setvacio para no volver a sacar esa ficha. solo sacamos
	 * fichas si quedan fichas que sacar, si no hay fichas devolvemos null
	 * 
	 * @return Devolvemos la casilla generada de la bolsa
	 */
	public Ficha getFicha() {
		// si no hay fichas no entramos ya que se quedaria el do while en ciclo
		// infinito.
		r = new Random();
		if (numeroFichas == 0)
			return null;
		else {
			int numero;
			do {
				numero = r.nextInt(miBolsa.length);
			} while (usado[numero]);
			// al sacar una ficha la marcamos que ya no esta vacia con lo
			// que nos sirve para saber si ha sido sacada o no
			usado[numero] = true;
			// restamos 1 a las fichas que quedan en la bolsa
			numeroFichas--;
			// return miCasilla;
			return miBolsa[numero];
		}
	}

	/**
	 * @return el numeroFichas
	 */
	public int getNumeroFichas() {
		return numeroFichas;
	}

	/**
	 * @return devuelve true o false si quedan fichas en la bolsa o no
	 */

	public boolean getQuedanFichas() {
		return (numeroFichas != 0);
	}

	/**
	 * Restablece los caracteres de los comodines despues de su uso
	 */
	public void restableceComodin() {
		for (int i = 0; i < Datos.NUMERO_COMODIN; i++) {
			miBolsa[Datos.NUMERO_FICHAS - (1 + i)].setLetra('*');
		}
	}

	/**
	 * Devuelve una cadena de texto con la representacion del objeto bolsa
	 * 
	 * @return la cadena de texto representando a la bolsa
	 */
	@Override
	public String toString() {
		String cadena;
		String enlace = "], \n";
		cadena = "Elementos totales en la bolsa: " + miBolsa.length;
		cadena = cadena + "\nElementos restantes en la bolsa: "
				+ getNumeroFichas() + "\nBolsa: [\n";

		for (int i = 0; i < miBolsa.length; i++) {
			cadena = cadena + "[Identificador ficha: "
					+ miBolsa[i].getIdentificador() + " [Letra= "
					+ miBolsa[i].getLetra() + ", Valor= "
					+ miBolsa[i].getValor() + "], usada= ";
			if (usado[i])
				cadena = cadena + "si";
			else
				cadena = cadena + "no";
			if (i == miBolsa.length - 1)
				enlace = "]]";
			cadena = cadena + enlace;
		}
		return cadena;
	}

}
