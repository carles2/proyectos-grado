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
	public void realizaTurno(Tablero tablero,Bolsa bolsa, Diccionario diccionario){
		// lo que esta sin el * es la lina que falta por hacer
		/*
		*hacer
		*	hacer
		*		selecciono jugar o paso turno
		*	mientras opcion no valida
		*	si juego
		*		hacer 
		*			seleciono mi ficha o tablero o validar
		*			hacer	// control de coordenada correcta
		*				selecciono coordenada
		*			mientras coordenada no valida 	// fin control coordenada
		*			si mi ficha
		*				hacer
		*					selecciono ficha (1-7)
		*				mientras posicion no valida
		*				añado a vector temporal de palabras mi ficha
		*				fin si mi ficha
		*			sino
		*			si es tablero
		*				copio la casilla de tablero a vector temporal de palabras
		*			fin si es tablero
		*			sino
		*				si es validar
		*					recorro vector temporal de palbras y calculo la puntuacion
		*						si esta en el diccionario, 
		*						incremento la puntuacion
		*						copio las fichas del vector temporal de palabras al tablero
		*						elimino las fichas del usuario
		*					fin si en diccionario
		*					borro vector temporal	
		*				fin si validar
		*		mientras no validar
		*	sino
		*		paso turno
		*hasta paso de turno
		*pido fichas neuvas a la bolsa

		*/
		boolean turno=false;
		boolean salida;
		int posx=0,posy=0,posicion;
		CadenaCasilla[] cadenaCasilla = new CadenaCasilla[15];
	    char caracter;
	    int contador=0;
	    boolean[] fichasNoUsadas= new boolean[7];
	    for (int i=0;i<cadenaCasilla.length;i++) cadenaCasilla[i]=new CadenaCasilla();
	    for (int i = 0;i<7;i++) fichasNoUsadas[i]=true;
		do{
			System.out.print("¿Quieres jugar o pasar turno?: (J/T) ");
            caracter=leerCaracter("JugaroPasar");
            //caracter tiene la opcion del juego

			if (caracter=='J'){// juego
				boolean validar=false;
				do{
					System.out.print("Selecciona tablero, mis fichas o validar: (T/M/V) ");
					caracter=leerCaracter("OpcionTurno");
					//en caracter tenemos el modo de juego, ahora pedimos las coordenada
					salida=false;
					if	((caracter=='M')||(caracter=='T'))
						do{
							System.out.print("Introduce la coordenada X a insertar: (0/14)");
							posx=leerEntero(14);
							System.out.print("Introduce la coordenada Y a insertar: (0/14)");
							posy=leerEntero(14);
							
							switch(caracter){
							case 'M': if (!tablero.getCasilla(posx, posy).isVacio())
										System.out.println("Las coordenada introducida ya esta en uso, intentalo de nuevo");
									  else salida=true;
									  break;
							case 'T': if (tablero.getCasilla(posx, posy).isVacio())
										System.out.println("La coordenada introducida no son de una ficha, intentalo de nuevo");
									  else salida=true;
									  break;
							}
						}while(!salida);
					salida=false;
					
					if (caracter=='M'){
						// pedimos la posicion de la ficha con la que jugamos y controlamos que ya estaba pedida
						System.out.print("Introduce la posicion de la ficha a usar: (0/6) ");
						do{
							posicion=leerEntero(6);
							if (fichasNoUsadas[posicion]==false) // la ficha ya ha sido usada
								System.out.println("Ya has usado la ficha especificada, no puedes repetirla");
							else {
								salida=true;
								fichasNoUsadas[posicion]=false;
							}
						}while(!salida);
		
						lasFichas[posicion].setPosicionX(posx);
						lasFichas[posicion].setPosicionY(posy);
						cadenaCasilla[contador].setCasilla(lasFichas[posicion]);
						cadenaCasilla[contador].setJugador(true);
						cadenaCasilla[contador].setPosicion(posicion);
						contador++;
					}
					if (caracter=='T'){
				
						cadenaCasilla[contador].setCasilla(tablero.getCasilla(posx, posy));
						cadenaCasilla[contador].setJugador(false);
						contador++;
						
					}
					if (caracter=='V'){
						validar=true;
					    for (int i = 0;i<7;i++) fichasNoUsadas[i]=true;
					}
				}while(!validar);

				
				//sacamos la palabra del vector temporal
				String palabraEvaluar="";
				int puntuacionProv=0;
				int multiplicador=1;
				int multiplicadorPalabra=1;
				for (int i=0;(cadenaCasilla[i].getCasilla()!=null)&&(i<15);i++){
					palabraEvaluar=palabraEvaluar.concat(String.valueOf(cadenaCasilla[i].getCasilla().getLetra()));
					
					//puntuacion
					if ((cadenaCasilla[i].getCasilla().isEspecial())&&(cadenaCasilla[i].getCasilla().isVacio())){
						if (cadenaCasilla[i].getCasilla().getTCasilla()==Datos.TipoCasilla.DL)
							multiplicador=2;
						if (cadenaCasilla[i].getCasilla().getTCasilla()==Datos.TipoCasilla.TL)
							multiplicador=3;
					}
					if ((cadenaCasilla[i].getCasilla().isEspecial())&&(!cadenaCasilla[i].getCasilla().isVacio())){
						if (cadenaCasilla[i].getCasilla().getTCasilla()==Datos.TipoCasilla.DP)
							multiplicadorPalabra=multiplicadorPalabra*2;
						if (cadenaCasilla[i].getCasilla().getTCasilla()==Datos.TipoCasilla.TP)
							multiplicadorPalabra=multiplicadorPalabra*3;
					}
					
                    puntuacionProv=puntuacionProv+(cadenaCasilla[i].getCasilla().getValor()*multiplicador);
                    multiplicador=1;
				}
				//buscamo en el diccionario
                if (diccionario.EsValida(palabraEvaluar)){//La palabra es válida
                         setPuntuacion(puntuacionProv*multiplicadorPalabra);
                         multiplicadorPalabra=1;
                         for (int i=0;(cadenaCasilla[i].getCasilla()!=null)&&(i<15);i++){
                             //solo añade las casillas del jugador y borra del jugador
                        	 if(cadenaCasilla[i].isJugador()){
                        		cadenaCasilla[i].getCasilla().setVacio(false);
                        		tablero.setCasilla(cadenaCasilla[i].getCasilla());
     							//tablero[cadenaCasilla[i].getCasilla().getPosicionX()][cadenaCasilla[i].getCasilla().getPosicionY()]=cadenaCasilla[i].getCasilla();
     							lasFichas[cadenaCasilla[i].getPosicion()]=null;
     						}
                         }
                 }
				contador=0;
				//borrado del vector hay que hacerlo tanto si esta en el diccionario como si no para el siguiente paso
				for(int i=0;i<cadenaCasilla.length;i++)
					cadenaCasilla[i]=null;
			}
			else // pasamos turno
				turno=true;
		}while (turno==false);
		//pedimos nuevas fichas
		setLasFichas(bolsa);
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
	 * Lee un carácter. Si se leen varios, se queda únicamente con el primero
	 * @return Caracterleido
	 */
	   public char leerCaracter(String modo){
	    BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));
	    char caracter=0;
	    String s=null;
	    boolean salida=false;
	    do{        
	       
	                try {
	                        s = Input.readLine();
	                s=s.toUpperCase();
	                caracter=s.charAt(0);
	                switch (modo)
	                {
	                case "HoV": if ((caracter!='H')&&(caracter!='V'))
	                                        System.out.println("Prueba a escribir la orientación de nuevo");
	                                        else salida=true;
	                                        break;
	                case "OpcionTurno":
	                                        if ((caracter!='M')&&(caracter!='T')&&(caracter!='V'))
	                                        System.out.println("Prueba a escribir la opcion correcta");
	                                        else salida=true;
	                                        break;
	                case "JugaroPasar":
	                                        if ((caracter!='J')&&(caracter!='T'))
	                                        System.out.println("Prueba a escribir la opcion correcta");
	                                        else salida=true;
	                                }
	               
	                } catch (IOException e) {
	                        e.printStackTrace();
	                }
	    }while (!salida);
	    return caracter;
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
	   }while(!salida);
	   return numero;
   }
   
}