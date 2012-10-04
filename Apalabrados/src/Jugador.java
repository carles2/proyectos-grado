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
			// ahora recore el vector de ficha y aï¿½ade las que neceiste
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
	
	/**
	 * Permite utilizar las fichas disponibles para crear una nueva palabra
	 * @return Palabra incompleta, todavÃ­a sin ser analizada
	 * @throws IOException ExcepciÃ³n de E/S
	 */
	protected Palabras UtilizarFichas() throws IOException
	{
		System.out.println("Escribe las posiciones de las fichas a introducir, y finaliza la introducciÃ³n con un 0");
		int posicion = 1; //PosiciÃ³n de la letra en el conjunto disponible
		int posicionX, posicionY; //PosiciÃ³n en abcisa y ordenada de la primera letra
		String palabra="";
		while(posicion!=0)
		{
			//Pedimos por teclado un nÃºmero entre 0 y 7
			posicion=leerEntero(7);
			//AÃ±adimos letra a letra a la palabra para crearla
			palabra.concat(Character.toString(lasFichas[posicion-1].getLetra()));
			//Sumamos la puntuaciÃ³n relativa de la palabra
		}
		System.out.println("Escribe la posiciÃ³n en abscisa de casilla inicial(X)");
		posicionX=leerEntero(15);
		System.out.println("Escribe la posiciÃ³n en ordenada de casilla inicial(Y)");
		posicionY=leerEntero(15);
		System.out.println("Escribe la orientaciÃ³n de la palabra (h/v)");
		boolean boolOrientacion=leerCaracter();
		//QUEDA CREAR LA PALABRA, HAY QUE TENER EN CUENTA LAS LETRAS EXISTENTES POR MEDIO, PROVENIENTES DE PALABRAS
		//YA ESCRITAS, ES DECIR, HABRÃ� QUE COMPROBAR UNA A UNA LAS CASILLAS DEL TABLERO A PARTIR DE LOS 
		//DATOS RECOPILADOS
		int[] posicionInicial=new int[2];
		posicionInicial[0]=posicionX;
		posicionInicial[1]=posicionY;
		Palabras palabraTemporal=new Palabras(palabra,posicionInicial,boolOrientacion);
		//Palabra temporal puede no contener la palabra completa, es decir, si en
		//el tablero se cruza en cualquier momento una palabra ya escrita, esta no estÃ¡
		//aÃ±adida en la palabra temporal.
		return palabraTemporal;
		//FALTA LA PUNTUACION RELATIVA!
	}
	
    /**
 * Lee un carácter. Si se leen varios, se queda únicamente con el primero
 * @return Caracterleido
 */
   public boolean leerCaracter(){
    BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));
    char caracter=0;
    String s=null;
    boolean salida=false;
    do{
		try {
			s = Input.readLine();
	    	s=s.toUpperCase();
	    	caracter=s.charAt(0);
	    	if ((caracter!='H')&&(caracter!='V'))
	    		 System.out.println("Prueba a escribir la orientación de nuevo");
	    	else salida=true;
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }while (salida);
    return (caracter=='H'?true:false);
}

   
    /**
     * Lee un entero. Sigue pidiendo el dato hasta que sea correcto.
     *
     * @param modo  establece el modo en que ha de producirse la entrada por teclado:
     *                              0 para permitir la entrada de números entre 0 y 15, 1 para números entre 0 y 7
     * @return Entero leido
     * @throws IOException
     */
   public int leerEntero(int limite){
	   BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));
	   String s=null;
	   int numero=-1;
	   boolean salida=false;
	   do{
		   try {
			   s = Input.readLine();
			   numero=Integer.parseInt(s);
			   if ((numero<0)||(numero>limite))
				   System.out.println("Error en el numero, prueba de nuevo.");
			   else  salida=true;
		   } catch (IOException e) {
				e.printStackTrace();
		   }
	   }while(salida);
	   return numero;
   }
}