/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
public class BusquedaPalabras extends Thread {
	private Tablero tablero;
	private PalabrasEncontradas palabrasencontradas;
	private int inicio;
	private int posicion;
	private boolean sentido;
	private String cadena = "";
	private boolean encontrado = false;
	private Diccionario diccionario;
	private int puntos = 0;
	private Puntuacion puntuacion;

	/**
	 * 
	 */
	public BusquedaPalabras() {

	}

	/**
	 * @param diccionario
	 * @param tablero
	 * @param palabrasencontradas
	 * @param inicio
	 * @param sentido
	 * @param puntuacion
	 */
	public BusquedaPalabras(Diccionario diccionario, Tablero tablero,
			PalabrasEncontradas palabrasencontradas, int inicio,
			boolean sentido, Puntuacion puntuacion) {
		this.diccionario = diccionario;
		this.tablero = tablero;
		this.palabrasencontradas = palabrasencontradas;
		this.inicio = inicio;
		this.sentido = sentido;
		this.puntuacion = puntuacion;
	}

	private int CalculoPosicion(int posicion, boolean sentido) {
		/*
		 * if (sentido){ return posicion; }else { return posicion+inicio*15; }
		 */

		return (sentido ? posicion + inicio * 15 : inicio + posicion * 15);
	}

	@Override
	public void run() {
		int x, y;
		int multiplicador = 1;
		int multiplicadorPalabra = 1;

		Casilla casillaAux;
		for (int i = 0; i < 15; i++) {
			if (sentido) {
				x = i;
				y = inicio;
			} else {
				x = inicio;
				y = i;
			}

			casillaAux = tablero.getCasilla(x, y);

			if (!casillaAux.isVacio()) {
				if (!encontrado) {
					posicion = i;
					encontrado = true;
				}

				// puntuacion
				if (casillaAux.isEspecial() && (casillaAux.isPrimeraVez())) {

					if (casillaAux.getTCasilla() == Datos.TipoCasilla.DL)
						multiplicador = 2;
					if (casillaAux.getTCasilla() == Datos.TipoCasilla.TL)
						multiplicador = 3;
				}

				if (casillaAux.isEspecial()) {
					if (casillaAux.getTCasilla() == Datos.TipoCasilla.DP)
						multiplicadorPalabra = 2;
					if (casillaAux.getTCasilla() == Datos.TipoCasilla.TP)
						multiplicadorPalabra = 3;
				}

				if (casillaAux.isPrimeraVez()) {
					casillaAux.setPrimeraVez(false);
					tablero.setCasilla(casillaAux);
				}

				puntos = puntos
						+ (casillaAux.getFicha().getValor() * multiplicador);
				multiplicador = 1;
				cadena = cadena + casillaAux.getFicha().getLetra();
			} else {
				if (encontrado) { // si es true es un espacio
					encontrado = false;
					PalabrasIntroducidas palabra = new PalabrasIntroducidas(
							cadena, CalculoPosicion(posicion, sentido), sentido);
					cadena = "";
					puntos = puntos * multiplicadorPalabra;
					multiplicadorPalabra = 1;
					if (palabra.getCadena().length() > 1) {
						if (!palabrasencontradas.EstaInsertada(palabra)) { // si
																			// la
																			// palabra
																			// no
																			// esta
																			// la
																			// inserto
							palabrasencontradas.setInsertarPalabra(palabra);

							if (diccionario.EsValida(palabra.getCadena())) { // si
																				// la
																				// palabra
																				// esta
																				// en
																				// el
																				// diccionario
								puntuacion.sumaPuntuacion(puntos);
								validadas(palabra);
							}
						}
						puntos = 0;
					}
				}
			}
		}
		if (encontrado) {
			PalabrasIntroducidas palabra = new PalabrasIntroducidas(cadena,
					CalculoPosicion(posicion, sentido), sentido);
			cadena = "";
			puntos = puntos * multiplicadorPalabra;
			multiplicadorPalabra = 1;
			if (!palabrasencontradas.EstaInsertada(palabra)) { // si la palabra
																// no esta la
																// inserto
				palabrasencontradas.setInsertarPalabra(palabra);
				if (diccionario.EsValida(palabra.getCadena())) { // si la
																	// palabra
																	// esta en
																	// el
																	// diccionario
					if (palabra.getCadena().length() > 1) {
						puntuacion.sumaPuntuacion(puntos);
						validadas(palabra);
					}
				}
				puntos = 0;
			}
		}
	}

	private void validadas(PalabrasIntroducidas palabras) {
		int x, y;
		x = palabras.getPosicion() % 15;
		y = palabras.getPosicion() / 15;
		for (int i = 0; i < palabras.getCadena().length(); i++) {
			if (!tablero.getCasilla(x, y).isValidada()) {
				tablero.getCasilla(x, y).setValidada(true);
			}
			if (palabras.isSentido()) {
				x++;
			} else {
				y++;
			}
		}
	}
}
