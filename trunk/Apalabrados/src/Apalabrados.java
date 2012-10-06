//prueba
public class Apalabrados {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tablero tablero;
		Jugador jugador1,jugador2;
		Bolsa bolsa;
		Diccionario diccionario=new Diccionario();
		bolsa = new Bolsa();
		tablero= new Tablero();
		jugador1 = new Jugador("Enrique",bolsa);
		jugador2 = new Jugador("Jose",bolsa);
		char caracter;
		int puntuacionProv;
		boolean turno;
		String palabraEvaluar;
		CadenaCasilla[] cadenaCasilla = new CadenaCasilla[15];
		//realiza el juego mientras queden fichas
		do{
			
			
			turno=false;
			//Y si hacemos la realización del turno aquí?
			do{
		    	System.out.print("¿Quieres jugar, pasar turno?: (J/T) ");
		    	caracter=jugador1.leerCaracter("JugaroPasar");
		    	if (caracter=='J'){// juego
		    		cadenaCasilla=jugador1.realizaTurno(tablero.getTablero(), bolsa);
		    		palabraEvaluar="";
		    		puntuacionProv=0;
		    		//Hallamos el String para saber si está en el diccionario
		    		for (int i=0;(cadenaCasilla[i]!=null)&&(i<15);i++)
		    		{
		    			palabraEvaluar=palabraEvaluar.concat(String.valueOf(cadenaCasilla[i].getCasilla().getLetra()));
		    			puntuacionProv=puntuacionProv+cadenaCasilla[i].getCasilla().getValor();
		    		}
		    		if (diccionario.EsValida(palabraEvaluar))
		    		//La palabra es válida
		    		{
		    			
		    		}
		    		else //La palabra no es válida
		    		{
		    			
		    		}
		    	}
		    	else
		    	{
		    		turno=true;
		    	}
			}while (turno==false);
			//pedimos nuevas fichas
			jugador1.setLasFichas(bolsa);
		    			
		    	
			
			
			
			
			
			
			
			
			jugador1.pintaFichas();
			jugador1.realizaTurno(tablero.getTablero(),bolsa);
			//jugador1.setLasFichas(bolsa);
			//jugador1.setPuntuacion(tablero.busqueda());
			// pinta la puntuacion
			System.out.println("puntuacion del jugador "+jugador1.getNombreJugador()+": "+jugador1.getPuntuacion()+" puntos");
			///
			System.out.println("numero de fichas restantes en la bolsa: "+ bolsa.getNumeroFichas());
			///
			tablero.pintaTablero();
			jugador2.pintaFichas();
			jugador2.realizaTurno(tablero.getTablero(),bolsa);
			//jugador2.setLasFichas(bolsa);
			//jugador2.setPuntuacion(tablero.busqueda());
			// pinta la puntuacion
			System.out.println("puntuacion del jugador "+jugador2.getNombreJugador()+": "+jugador2.getPuntuacion()+" puntos");
			///
			System.out.println("numero de fichas restantes en la bolsa: "+ bolsa.getNumeroFichas());
			///
			
			tablero.pintaTablero();
		}while (bolsa.getQuedanFichas());
	}
}
