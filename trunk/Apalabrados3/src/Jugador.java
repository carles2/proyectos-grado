import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Jugador extends JPanel{
	private Ficha[] lasFichas = new Ficha[7];
	private String nombreJugador;
	private int puntuacion;
	private int numeroJuegos;
	private boolean pasoTurnos;
	private boolean[] jugador;
	private int[] posicionLetra;
	private Casilla[] cadenaCasilla;
	private boolean[] fichasNoUsadas = new boolean[7];
	// variables usada para el control de la filas horizontales y verticlaes
	private int posxanterior;
	 private int posyanterior;
	 private int direccion;
	//
	 private Image soporte;

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
		for (int i =0 ;i<fichasNoUsadas.length;i++){
			fichasNoUsadas[i]=true;
		}
		
		soporte = new ImageIcon(Datos.SOPORTE_FICHAS).getImage();

		puntuacion = 0; // Cada jugador comienza teniendo 0 puntos
		// for (int i=0;i<lasFichas.length;i++) lasFichas[i]=new Casilla();
		setLasFichas(bolsa);
		reinciaControlVH(); // iniciamos el control de las fichas
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
		for (int i = 0; (i < 7) && (bolsa.getQuedanFichas()); i++) {
			// ahora recore el vector de ficha y aï¿½ade las que neceiste
			// para que esto funcione una vez sacadas del jugador han de ponerse a null
			if (lasFichas[i] == null) {
				lasFichas[i] = bolsa.getFicha();
				fichasNoUsadas[i]=true; // marcamos las fichas que se pueden utilizar
				// al pedir nuevas ficha esa ficah ya se puede utilizar
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
		char caracter;
		int contador = 0;

		int sumaCuarenta = 0;
		do {
			System.out.print("Jugador "+ getNombreJugador()+" ¿Quieres jugar o pasar turno?: (J/T) ");
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
					// coordenadas
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
									salida = controlVH(posx, posy);
								break;
							case 'T':
								if (tablero.getCasilla(posx, posy).isVacio())
									System.out.println("La coordenada introducida no son de una ficha, intentalo de nuevo");
								else
									salida = controlVH(posx, posy);
								break;
								
							}
							if (!salida){ // si llegamos aqui es que las cooredeandas no son validas
								System.out.println("Las letas han de estar en la misma fila y columna. De derecha a izquierda y de arriba a abajo");
								System.out.println("Intentalo de nuevo: ");
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
						// llamamos a pintar fichas "que se usan"
						pintaFichas();
						repaint();
					}
					if (caracter == 'T') {

						cadenaCasilla[contador] = tablero.getCasilla(posx, posy);
						cadenaCasilla[contador].setFicha(tablero.getCasilla(posx, posy).getFicha());
						jugador[contador] = false;
						contador++;

					}
					if (caracter == 'V') {
						validar = true;
						reinciaControlVH(); // reiniciamos el conrol de las letras H o V
					      // se necesita por si la palabra es incorrecta que pinte toda las fichas
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
						System.out.print("Introduce la letra con la que vas a jugar en el comodin: ");
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
							lasFichas[posicionLetra[i]]=null;
						}
						else {
							tablero.setCasilla(cadenaCasilla[i]);
						}
					}
				}
				sumaCuarenta = 0;
				contador = 0;
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
		repaint();
		///////////////////////////////////
		System.out.println("quedan en la bolsa "+bolsa.getNumeroFichas()+" fichas");
		//////////////////////////////////
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
	 * Establece el nombre del jugador
	 * @param nombre
	 */
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	/**
	 * @return devuelve la puntuacion del jugaor 
	 */
	public int getPuntuacion() {
		return puntuacion;
	}

	/**
	 * Establece la nueva puntuacion del jugador sumando a la anterior
	 * 
	 * @param puntuacion, La puntuacion a sumar a la anterior.
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = this.puntuacion + puntuacion;
	}

	/**
	 * Pinta las fichas del jugador
	 */
	public void pintaFichas() {
		String pintaLetra=null;
		String pintaValor=null;
		System.out.println("Nombre del jugador: " + getNombreJugador()+"\tPuntuacion: "+getPuntuacion());
		System.out.println("-----------------------------");
		for(int i=0;i<7;i++){
			System.out.print("| " + i + " ");
		}
		System.out.println("| Posicion");
		System.out.println("-----------------------------");
		for (int i = 0; i < 7; i++) {
			if (fichasNoUsadas[i]==false)
				pintaLetra=" ";
			else
				pintaLetra=String.valueOf(lasFichas[i].getLetra());
			
			System.out.print("| " + pintaLetra + " ");
		}
		System.out.println("| Letras");
		System.out.println("-----------------------------");
		for (int i = 0; i < 7; i++) {
			if (fichasNoUsadas[i]==false)
				pintaValor=" ";
			else pintaValor=String.valueOf(lasFichas[i].getValor());
			System.out.print("| " + pintaValor + " ");
		}
		System.out.println("| Valor de las letras");
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
					if (Character.isAlphabetic(caracter)){
						if ((caracter == 'K') && (caracter == 'W')) // si es la k o w
							System.out.println("Solo son validas las letras menos la K y la W: ");
						else
							salida = true;
					}
					else{ // si se escriben caracteres que no son letras
						System.out.println("Solo son validas las letras menos la K y la W: ");
					}
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
	
	/**
	 * Este metodo devuelve true o false si las coordeandas introducidas esta en linea con las anteriores
	 * 
	 * @param posx, Posicion de la coordenada x
	 * @param posy, Posicion de la coordeanda y
	 * @return True, si las coordenadas introducidas estan en linea con las anteriores ya sea vertical u horizontal
	 * devuelve false, en caso de que no esten alineadas.
	 * 
	 */
	private boolean controlVH(int posx, int posy){
		if (posxanterior==-1){
			posxanterior=posx;
			posyanterior=posy;
			return true;
		}
		else{
			if (direccion==0){
				if ((posx==posxanterior)&& (posy>posyanterior)){
					direccion=1; //es vertical
					return true;
				}
				if ((posy==posyanterior)&& (posx>posxanterior)){
					direccion=-1;//es horizontal
					return true;
				}
			}
			else {
				if (direccion==1)
					return ((posx==posxanterior)&& (posy>posyanterior));
				else 
					return ((posy==posyanterior)&& (posx>posxanterior));
			}
		}
		return false;
	}
	
	/**
	 * Inicializa las variables usadas en el controlo de las filas verticales y horizontales
	 * Este metodo ha de se llamado cada vez que se valide una palabra para poder controlar la 
	 * siguiente palabra introducida
	 */
	private void reinciaControlVH(){
		posxanterior=-1;
		posyanterior=-1;
		direccion=0;
	}

	public void paintComponent(Graphics g){
		//Dibujar el fondo del formulario 
		super.paintComponents(g);

		//Dibujar el tablero
		g.drawImage(soporte, 0, 0, 369, 68, this);
		
		//Dibujar las fichas que se encuentran sobre el tablero
		int x=15;
		for(int i=0; i<7; i++){
			if (fichasNoUsadas[i]){
				g.drawImage(lasFichas[i].getImagen(), x, 15, 35, 35, this);
			}
			x=x+50;

		}
		
		

		setOpaque(false);

	}
}