import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 
public class Jugador {
	private Casilla[] lasFichas= new Casilla[7];
	private String nombreJugador;
	private int puntuacion;
	
	
	Jugador(Bolsa bolsa){
		this ("Jugador",bolsa);
	}
	
	/**
	 * Constructor parametrizado
	 * @param Nombre Nombre del jugador
	 * @param bolsa Bolsa de letras
	 */
	Jugador(String Nombre,Bolsa bolsa){
		nombreJugador=Nombre;
		puntuacion =0; //Cada jugador comienza teniendo 0 puntos
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
		boolean noHayFichas=false; 	//Comprueba si quedan fichas que coger,
									//si no, no tiene sentido recorrer el bucle
		for (int i=0;(i<7)&&(noHayFichas==false);i++){
			// no hace falta esta instancia de casilla ya que en la bolsa estan instanciadas
			// por lo tanto al igualar estamos enlazando con las fichas de la bolsa
			// lasFichas[i] = new Casilla();
			// ahora recore el vector de ficha y a�ade las que neceiste
			// para que esto funcione una vez sacadas del jugador han de ponerse a null
			if (lasFichas[i]==null)
			{
				lasFichas[i]=bolsa.getFicha();
				if (lasFichas[i]==null) noHayFichas=true;
			}	
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
	
	protected void UtilizarFichas() throws IOException
	{
		System.out.println("Escribe las posiciones de las fichas a introducir, y finaliza la introducción con un 0");
		int posicion = 1; //Posición de la letra en el conjunto disponible
		int posicionX, posicionY; //Posición en abcisa y ordenada de la primera letra
		int puntuacionRelativa=0;
		char orientacion;
		String palabra="";
		while(posicion!=0)
		{
			//Pedimos por teclado un número entre 0 y 7
			posicion=leerEntero(1);
			//Añadimos letra a letra a la palabra para crearla
			palabra.concat(Character.toString(lasFichas[posicion-1].getLetra()));
			//Sumamos la puntuación relativa de la palabra
			puntuacionRelativa+=lasFichas[posicion-1].getValor();
		}
		System.out.println("Escribe la posición en abscisa de casilla inicial(X)");
		posicionX=leerEntero(0);
		System.out.println("Escribe la posición en ordenada de casilla inicial(Y)");
		posicionY=leerEntero(0);
		System.out.println("Escribe la orientación de la palabra (h/v)");
		orientacion=leerCaracter();
		//QUEDA CREAR LA PALABRA, HAY QUE TENER EN CUENTA LAS LETRAS EXISTENTES POR MEDIO, PROVENIENTES DE PALABRAS
		//YA ESCRITAS, ES DECIR, HABRÁ QUE COMPROBAR UNA A UNA LAS CASILLAS DEL TABLERO A PARTIR DE LOS 
		//DATOS RECOPILADOS
		
	}
	
	/**
	 * Convertimos la orientación de la palabra en carácter a booleano
	 * @param orientacion Carácter de entrada (h o v)
	 * @return booleano (true horizontal y false vertical)
	 */
	private boolean ConversionOrientacion(char orientacion)
	{
		boolean bOrientacion;
		if (orientacion=='h'||orientacion=='H')
			bOrientacion=true;
		else
			bOrientacion=false;
		return bOrientacion;
	}
	 /**
     * Lee un carácter. Si se leen varios, se queda únicamente con el primero
     * @return Caracterleido
     * @throws IOException 
     */
    public char leerCaracter() throws IOException {
    	BufferedReader Input;
    	Input = new BufferedReader(new InputStreamReader(System.in));
    	char caracter;
    	while (true) {
            String s = Input.readLine();
            try {
                caracter=s.trim().charAt(0);
                if ((caracter!='h')&&(caracter!='H')&&(caracter!='v')&&(caracter!='V'))
                throw new StringIndexOutOfBoundsException();
                return caracter;
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Prueba a escribir la orientación de nuevo");
            }
        }
    }

	
	/**
	 * Lee un entero. Sigue pidiendo el dato hasta que sea correcto.
	 * 
	 * @param modo  establece el modo en que ha de producirse la entrada por teclado:
	 * 				0 para permitir la entrada de números entre 0 y 15, 1 para números entre 0 y 7
	 * @return Entero leido
	 * @throws IOException
	 */
	protected static int leerEntero(int modo) throws IOException {
		BufferedReader Input;
		Input = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s = Input.readLine();
			int numero;
			try {
				numero=Integer.parseInt(s);
				switch(modo)
				{
				case 0:
					//En modo 0, si el número no se encuentra entre 0 y 15, lanza la excepción
					if (numero<0||numero>15)
					{
						throw new NumberFormatException();
					}
					break;
				case 1:
					//En modo 1, si el número no se encuentra entre 0 y 7, lanza la excepción
					if (numero<0||numero>7)
					{
						throw new NumberFormatException();
					}
				}
				return numero;
			} catch (NumberFormatException e) {
				System.out.println("Error en el numero, prueba de nuevo.");
			}
		}
	}
}