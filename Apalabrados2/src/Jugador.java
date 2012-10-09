import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jugador {
	private Ficha[] lasFichas = new Ficha[7];
	private String nombreJugador;
	private int puntuacion;
	private int numeroJuegos;
	private boolean pasoTurnos;
	private boolean[] jugador;
	private int[] posicionLetra;
	private Casilla[] cadenaCasilla;

	Jugador(Bolsa bolsa) {
		this("Jugador", bolsa);
	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param Nombre del jugador
	 * @param bolsa de letras
	 */
	Jugador(String Nombre, Bolsa bolsa) {
		nombreJugador = Nombre;
		numeroJuegos = 0;
		pasoTurnos = false;

		jugador = new boolean[15];
		posicionLetra = new int[15];
		cadenaCasilla = new Casilla[15];
		for (int i = 0; i < cadenaCasilla.length; i++)
			cadenaCasilla[i] = new Casilla();

		puntuacion = 0; // Cada jugador comienza teniendo 0 puntos
		// for (int i=0;i<lasFichas.length;i++) lasFichas[i]=new Casilla();
		setLasFichas(bolsa);
	}

	/**
	 * @return delvuelve las fichas del jugador para pintarlas
	 */
	public Ficha[] getLasFichas() {
		return lasFichas;
	}

	/**
	 * @param establece
	 *            las fichas del jugador hay que hacerlo aleatorio tirando de un
	 *            vector donde este el par letra-valor y segun el numero
	 *            aleatorio cogerlo
	 */
	public void setLasFichas(Bolsa bolsa) {
		boolean noHayFichas = false; // Comprueba si quedan fichas que coger,
										// si no, no tiene sentido recorrer el
										// bucle
		for (int i = 0; (i < 7) && (noHayFichas == false); i++) {
			// ahora recore el vector de ficha y aï¿½ade las que neceiste
			// para que esto funcione una vez sacadas del jugador han de ponerse
			// a null
			if (lasFichas[i] == null) {
				lasFichas[i] = bolsa.getFicha();
				if (lasFichas[i] == null)
					noHayFichas = true;
			}
		}
	}

	/**
	 * realiza las acciones de las fichas.
	 */
	public void realizaTurno(Tablero tablero, Bolsa bolsa, Diccionario diccionario) {
		boolean turno = false;
		boolean salida;
		int posx = 0, posy = 0, posicion;
		// CadenaCasilla[] cadenaCasilla = new CadenaCasilla[15];
		char caracter;
		int contador = 0;
		boolean[] fichasNoUsadas = new boolean[7];

		for (int i = 0; i < 7; i++)
			fichasNoUsadas[i] = true;
		int sumaCuarenta = 0;
		do {
			System.out.print("¿Quieres jugar o pasar turno?: (J/T) ");
			caracter = leerCaracter("JugaroPasar");
			// caracter tiene la opcion del juego

			if (caracter == 'J') {// juego
				numeroJuegos = 0;
				pasoTurnos = false;
				boolean validar = false;
				do {
					System.out.print("Selecciona tablero, mis fichas o validar: (T/M/V) ");
					caracter = leerCaracter("OpcionTurno");
					// en caracter tenemos el modo de juego, ahora pedimos las
					// coordenada
					salida = false;
					if ((caracter == 'M') || (caracter == 'T'))
						do {
							System.out.print("Introduce la coordenada X a insertar: (0/14)");
							posx = leerEntero(14);
							System.out.print("Introduce la coordenada Y a insertar: (0/14)");
							posy = leerEntero(14);

							switch (caracter) {
							case 'M':
								if (!tablero.getCasilla(posx, posy).isVacio())
									System.out.println("Las coordenada introducida ya esta en uso, intentalo de nuevo");
								else
									salida = true;
								break;
							case 'T':
								if (tablero.getCasilla(posx, posy).isVacio())
									System.out.println("La coordenada introducida no son de una ficha, intentalo de nuevo");
								else
									salida = true;
								break;
							}
						} while (!salida);
					salida = false;

					if (caracter == 'M') {
						// pedimos la posicion de la ficha con la que jugamos y
						// controlamos que ya estaba pedida
						System.out.print("Introduce la posicion de la ficha a usar: (0/6) ");
						do {
							posicion = leerEntero(6);
							if (fichasNoUsadas[posicion] == false) // la ficha ya ha sido usada
								System.out.println("Ya has usado la ficha especificada, no puedes repetirla");
							else {
								salida = true;
								fichasNoUsadas[posicion] = false;
							}
						} while (!salida);

						cadenaCasilla[contador].setPosicionX(posx);
						cadenaCasilla[contador].setPosicionY(posy);

						cadenaCasilla[contador].setFicha(lasFichas[posicion]);
						jugador[contador] = true;
						posicionLetra[contador] = posicion;

						contador++;
						sumaCuarenta++;
					}
					if (caracter == 'T') {

						cadenaCasilla[contador] = tablero.getCasilla(posx, posy);
						jugador[contador] = false;
						contador++;

					}
					if (caracter == 'V') {
						validar = true;
						for (int i = 0; i < 7; i++)
							fichasNoUsadas[i] = true;
					}
				} while (!validar);

				// sacamos la palabra del vector temporal
				String palabraEvaluar = "";
				int puntuacionProv = 0;
				int multiplicador = 1;
				int multiplicadorPalabra = 1;
				for (int i = 0; (cadenaCasilla[i].getFicha() != null) && (i < 15); i++) {

					// si es comodin pedir letra y concadenar sino concadenar
					if (cadenaCasilla[i].getFicha().getLetra() == '*') {
						System.out.println("Introduce la letra con la que vas a jugar en el comodin: ");
						caracter = leerCaracter("Comodin");
						palabraEvaluar = palabraEvaluar.concat(Character.toString(caracter));
					} else
						palabraEvaluar = palabraEvaluar.concat(Character.toString(cadenaCasilla[i].getFicha().getLetra()));

					// puntuacion
					if ((cadenaCasilla[i].isEspecial())	&& (cadenaCasilla[i].isVacio())) {
						if (cadenaCasilla[i].getTCasilla() == Datos.TipoCasilla.DL)
							multiplicador = 2;
						if (cadenaCasilla[i].getTCasilla() == Datos.TipoCasilla.TL)
							multiplicador = 3;
					}
					if ((cadenaCasilla[i].isEspecial())	&& (!cadenaCasilla[i].isVacio())) {
						if (cadenaCasilla[i].getTCasilla() == Datos.TipoCasilla.DP)
							multiplicadorPalabra = multiplicadorPalabra * 2;
						if (cadenaCasilla[i].getTCasilla() == Datos.TipoCasilla.TP)
							multiplicadorPalabra = multiplicadorPalabra * 3;
					}

					puntuacionProv = puntuacionProv	+ (cadenaCasilla[i].getFicha().getValor() * multiplicador);
					multiplicador = 1;
				}
				// buscamos en el diccionario

				if (diccionario.EsValida(palabraEvaluar)) {// La palabra es válida
					
					// if (true){ // para debug y poder meter cosas que no estan en el diccionario. a borrar en el futuro
					if (sumaCuarenta == 7)
						puntuacionProv = puntuacionProv + 40;
					setPuntuacion(puntuacionProv * multiplicadorPalabra);
					multiplicadorPalabra = 1;
					for (int i = 0; (cadenaCasilla[i].getFicha() != null) && (i < 15); i++) {
						// solo añade las casillas del jugador y borra del  jugador
						if (jugador[i] == true) {
							cadenaCasilla[i].setVacio(false);
							tablero.setCasilla(cadenaCasilla[i]);
							lasFichas[posicionLetra[i]] = null;
							// lasFichas[cadenaCasilla[i].getPosicion()]= new Ficha(); /////????
						}
					}
				}
				sumaCuarenta = 0;
				contador = 0;
				// borrado del vector hay que hacerlo tanto si esta en el
				// diccionario como si no para el siguiente paso
				for (int i = 0; i < cadenaCasilla.length; i++)
					//
					cadenaCasilla[i] = null;
			} else {// pasamos turno
				if (pasoTurnos == true)
					numeroJuegos++;
				else
					pasoTurnos = true;
				turno = true;
			}
		} while (turno == false);
		// pedimos nuevas fichas
		setLasFichas(bolsa);
	}

	/**
	 * 
	 * @return delvuelve el numero de turnos pasados consecutivamente.
	 */
	public int getNumeroTurnosPasados() {
		return numeroJuegos;
	}

	/**
	 * @return devuelve el nombre del jugador
	 */
	public String getNombreJugador() {
		return nombreJugador;
	}

	/**
	 * @param Establece
	 *            el nombre del jugador
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
	 * @param establece
	 *            la nueva puntuacion
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = this.puntuacion + puntuacion;
	}

	public void pintaFichas() {

		System.out.println("Nombre del jugador: " + getNombreJugador());
		System.out.println("-----------------------------");
		for (int i = 0; i < 7; i++) {
			System.out.print("| " + lasFichas[i].getLetra() + " ");
		}
		System.out.println("|");
		System.out.println("-----------------------------");
		for (int i = 0; i < 7; i++) {
			System.out.print("| " + lasFichas[i].getValor() + " ");
		}
		System.out.println("|");
		System.out.println("-----------------------------");

	}

	/**
	 * Lee un carácter. Si se leen varios, se queda únicamente con el primero
	 * 
	 * @return Caracterleido
	 */
	public char leerCaracter(String modo) {
		BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));
		char caracter = 0;
		String s = null;
		boolean salida = false;
		do {

			try {
				s = Input.readLine();
				s = s.toUpperCase();
				caracter = s.charAt(0);
				switch (modo) {
				case "Comodin":
					if (Character.isAlphabetic(caracter))
						if ((caracter != 'K') && (caracter != 'W'))
							System.out.println("Solo son validas las letras menos la K y la W");
						else
							salida = true;
					break;
				case "HoV":
					if ((caracter != 'H') && (caracter != 'V'))
						System.out.println("Prueba a escribir la orientación de nuevo");
					else
						salida = true;
					break;
				case "OpcionTurno":
					if ((caracter != 'M') && (caracter != 'T') && (caracter != 'V'))
						System.out.println("Prueba a escribir la opcion correcta");
					else
						salida = true;
					break;
				case "JugaroPasar":
					if ((caracter != 'J') && (caracter != 'T'))
						System.out.println("Prueba a escribir la opcion correcta");
					else
						salida = true;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!salida);
		return caracter;
	}

	/**
	 * Lee un entero. Sigue pidiendo el dato hasta que sea correcto.
	 * 
	 * @param modo
	 *            establece el modo en que ha de producirse la entrada por
	 *            teclado: 0 para permitir la entrada de números entre 0 y 15, 1
	 *            para números entre 0 y 7
	 * @return Entero leido
	 * @throws IOException
	 */
	public int leerEntero(int limite) {
		BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		int numero = -1;
		boolean salida = false;
		do {
			try {
				s = Input.readLine();
				numero = Integer.parseInt(s);
				if ((numero < 0) || (numero > limite))
					System.out.println("Error en el numero, prueba de nuevo.");
				else
					salida = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!salida);
		return numero;
	}

}