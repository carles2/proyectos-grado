import java.io.DataInputStream;
import java.util.Random;

public class Bolsa {

	private int numeroFichas;
	private Random r;
	private Ficha[] miBolsa;
	private boolean[] usado;

	Bolsa() {
		
		/**
		 * distribucion de letras segun el scrabbel original, a modificar segun
		 * la distribucion de apalabrados
		 *  0 puntos: dos comodines
		 *  1 punto: A*12, E*12, I*6, L*4, N*5, O*9, R*5, S*6, T*4, U*5, 
		 *  2 puntos: D*5, G*2 
		 *  3 puntos: B*2, C*4, M*2, P*2 
		 *  4 puntos: F*1, H*2, V*1, Y*1 
		 *  5 putnos: Q*1
		 *  8 puntos: J*1, X*1 
		 *  10 puntos: �*1, Z*1 no esta la K ni la W, pero se podrian a�adir a la de 10 puntos
		 */

/* 
		int[] abecedario[]=new int[26][]; //Matriz Letra|Tupla(Valor|Cantidad)
		int iterador=0;
		for (int i=65;i<=90;i++)
		{
			if ((i==75)||(i==87))
			{
				i++;
			}
			abecedario[iterador]=HallarTuplaVC(i);
			iterador++;
		}
		//Añadimos la Ñ y el comodín manualmente
		abecedario[iterador]=HallarTuplaVC(165);
		iterador++;
		abecedario[iterador]=HallarTuplaVC(42);
*/		
		
		Object abecedario[][] = { 
				
				{ 65, 1, 12,Datos.LETRA_A}, // Letra A, Valor de A, numero de A, imagen de la ficha de A
				{ 66, 3, 2 ,Datos.LETRA_B}, // Letra B
				{ 67, 3, 4, Datos.LETRA_C }, // Letra C
				{ 68, 2, 5,Datos.LETRA_D }, // Letra D
				{ 69, 1, 12,Datos.LETRA_E }, // Letra E
				{ 70, 4, 1, Datos.LETRA_F }, // Letra F
				{ 71, 2, 2,Datos.LETRA_G}, // Letra G
				{ 72, 4, 2,Datos.LETRA_H}, // Letra H
				{ 73, 1, 6, Datos.LETRA_I}, // Letra I
				{ 74, 8, 1,Datos.LETRA_J}, // Letra J
				//{75,5,1, Datos.Letra_K}, // Letra K
				{ 76, 1, 4, Datos.LETRA_L}, // Letra L
				{ 77, 3, 2, Datos.LETRA_M}, // Letra M
				{ 78, 1, 5,Datos.LETRA_N }, // Letra N
				{ 165, 10, 1, Datos.LETRA_ENIE}, // Letra �
				{ 79, 1, 9, Datos.LETRA_O}, // Letra O
				{ 80, 3, 2, Datos.LETRA_P}, // Letra P
				{ 81, 5, 1, Datos.LETRA_Q}, // Letra Q
				{ 82, 1, 5, Datos.LETRA_R}, // Letra R
				{ 83, 1, 6, Datos.LETRA_S}, // Letra S
				{ 84, 1, 4, Datos.LETRA_T}, // Letra T
				{ 85, 1, 5, Datos.LETRA_U}, // Letra U
				{ 86, 4, 1, Datos.LETRA_V}, // Letra V
				//{87, 10,1, Datos.LETRA_W}, // Letra W
				{ 88, 8, 1, Datos.LETRA_X}, // Letra X
				{ 89, 4, 1, Datos.LETRA_Y}, // Letra Y
				{ 90, 10, 1, Datos.LETRA_Z}, // Letra Z
				{ 42, 0, 2, Datos.LETRA_COMODIN} // Comodin *
		};
            



		usado = new boolean[Datos.NUMERO_FICHAS];
		numeroFichas = Datos.NUMERO_FICHAS;
		miBolsa = new Ficha[Datos.NUMERO_FICHAS];
		int aux;
		
// /*        // segundo metodo de creacion de las fichas
		aux=0;
       for (int i = 0; i < abecedario.length; i++) {
                  for (int j = 0; j < (int)abecedario[i][2]; j++) {
                          miBolsa[aux] = new Ficha((char)(int)abecedario[i][0],(int) abecedario[i][1],(String) abecedario[i][3]);
                          aux++;
                  }
          }
        
/*
		int aux = 0;
		int[] tuplaAux=new int[2];
		int tope;
		int iteAux=0;
		for (int i = 0; i < Datos.NUMERO_FICHAS; i++)
			usado[i] = false; // control si se usa o no una variable.
		for (int i = 0; i < abecedario.length; i++) {
			aux=i+65;
			if (aux>=75)
			{
				aux++;
				if (aux>=87)
				{
					aux++;
					if (aux==91)
					{
						aux=165;
					}
					if (aux==92)
					{
						aux=42;
					}
				}
			}
			
			
			tuplaAux=abecedario[i];
			
			tope=tuplaAux[1];
			while (tope!=0)
			{
				miBolsa[iteAux] = new Ficha((char) aux,tuplaAux[0]);
				iteAux++;
				tope--;
				
			}
			
			
		}
		//ESTO SON PRUEBAS - NO OLVIDAR BORRAR
		MostrarBolsa();
		System.out.println(miBolsa.length);
		ModificarCantidadCaracterenBolsa('A',0);
		System.out.println(miBolsa.length);
		MostrarBolsa();
		ModificarValorCaracterenBolsa('B',344);
		MostrarBolsa();
		System.out.println(toString());
*/		
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
		return (numeroFichas!=0);
	}

	/**
	 * Muestra la bolsa de fichas
	 */
	private void MostrarBolsa()
	{
		int cantidad=-1;
		char caracter=' ';
		for(int i=0;i<miBolsa.length;i++)
		{
			if (caracter==miBolsa[i].getLetra()) //Si el caracter está repetido
			{
				cantidad++;
			}
			else //Si no se ha repetido el caracter
			{
				if (cantidad>=0)
				System.out.print(cantidad);
				System.out.println();
				caracter=miBolsa[i].getLetra();
				System.out.print(caracter+" ");
				System.out.print(miBolsa[i].getValor()+" ");
				cantidad=1;
			}
		}
		System.out.print(cantidad);
		System.out.println();
	}
	
	public String toString(){
		String cadena;
		String enlace="], \n";
		cadena="Elementos totales en la bolsa: "+miBolsa.length;
		cadena=cadena+"\nElementos restantes en la bolsa: "+getNumeroFichas()+"\nBolsa: [\n";
		
		for(int i =0; i<miBolsa.length;i++){
			cadena=cadena+"[Ficha numero "+i+" [Letra= "+miBolsa[i].getLetra()+", Valor= "+miBolsa[i].getValor()
					+"], usada= ";
			if (usado[i])
				cadena=cadena+"si";
			else 
				cadena=cadena+"no";
			if (i==miBolsa.length-1)
				enlace="]]";
			cadena=cadena+enlace;
		}
		return cadena;
	}
		

	/**
	 * Modifica la cantidad de fichas en la bolsa dado el caracter
	 * @param caracter Caracter del que modificar su cantidad
	 * @param cantidadNueva Nueva cantidad de fichas con ese caracter
	 */
	private void ModificarCantidadCaracterenBolsa(char caracter,int cantidadNueva)
	{
		int cantidadActual=0;
		int diferencia,total;
		for(int i=0;i<miBolsa.length;i++)
		{
			if (caracter==miBolsa[i].getLetra()) //Si el caracter está repetido
			{
				cantidadActual++;
			}
		}
		diferencia=cantidadNueva-cantidadActual;
		total=97+diferencia;
		
		int iteAux=0;
		int ite=0;
		
		Ficha[] miBolsaAux = new Ficha[total];
		while (ite<miBolsa.length)
		{
			if ((cantidadNueva!=0)||(miBolsa[ite].getLetra()!=caracter))
			{
				miBolsaAux[iteAux] = miBolsa[ite];
				cantidadNueva--;
				iteAux++;
			}
			ite++;
		}
		
		setMiBolsa(miBolsaAux);
	}
	
	/**
	 * Método qe permite modificar el valor de una ficha dado su caracter
	 * @param caracter Caracter de la ficha
	 * @param valorNuevo Nuevo valor de la ficha
	 */
	private void ModificarValorCaracterenBolsa(char caracter,int valorNuevo)
	{
		int ite=0;
		while (ite<miBolsa.length)
		{
			if (miBolsa[ite].getLetra()==caracter)
			{
				miBolsa[ite].setValor(valorNuevo);
			}
			ite++;
		}
	}
	
	/**
	 * Getter MiBolsa
	 * @return bolsa de fichas
	 */
	public Ficha[] getMiBolsa() {
		return miBolsa;
	}

	/**
	 * Setter MiBolsa
	 * @param miBolsa Bolsa a establecer
	 */
	public void setMiBolsa(Ficha[] miBolsa) {
		this.miBolsa = miBolsa;
	}

	public boolean[] getUsado() {
		return usado;
	}

	public void setUsado(boolean[] usado) {
		this.usado = usado;
	}

	public void setNumeroFichas(int numeroFichas) {
		this.numeroFichas = numeroFichas;
	}

	/**
	 * Halla el valor y la cantidad de fichas de una determinada letra
	 * @param numLetra Número ASCII de la letra
	 * @return tupla de Valor y Cantidad
	 */
	private int[] HallarTuplaVC(int numLetra)
	{
		int[] tupla=new int[2];
		try{
			switch(numLetra)
			{
			case 65: 
				tupla[0]=1; //Valor
				tupla[1]=12; //Cantidad
				break;
			case 66: 
				tupla[0]=3; 
				tupla[1]=2; 
				break;
			case 67: 
				tupla[0]=3; 
				tupla[1]=4;
				break;
			case 68: 
				tupla[0]=2; 
				tupla[1]=5; 
				break;
			case 69: 
				tupla[0]=1; 
				tupla[1]=12;
				break;
			case 70: 
				tupla[0]=4; 
				tupla[1]=1; 
				break;
			case 71: 
				tupla[0]=2; 
				tupla[1]=2;
				break;
			case 72: 
				tupla[0]=4; 
				tupla[1]=2; 
				break;
			case 73: 
				tupla[0]=1; 
				tupla[1]=6;
				break;
			case 74: 
				tupla[0]=8; 
				tupla[1]=1; 
				break;
			case 76: 
				tupla[0]=1; 
				tupla[1]=4;
				break;
			case 77: 
				tupla[0]=3; 
				tupla[1]=2; 
				break;
			case 78: 
				tupla[0]=1; 
				tupla[1]=5;
				break;
			case 79: 
				tupla[0]=1; 
				tupla[1]=9;
				break;
			case 80: 
				tupla[0]=3; 
				tupla[1]=2; 
				break;
			case 81: 
				tupla[0]=5; 
				tupla[1]=1;
				break;
			case 82: 
				tupla[0]=1; 
				tupla[1]=5; 
				break;
			case 83: 
				tupla[0]=1; 
				tupla[1]=6;
				break;
			case 84: 
				tupla[0]=1; 
				tupla[1]=4; 
				break;
			case 85: 
				tupla[0]=1; 
				tupla[1]=5;
				break;
			case 86: 
				tupla[0]=4; 
				tupla[1]=1; 
				break;
			case 88: 
				tupla[0]=4; 
				tupla[1]=1;
				break;
			case 89: 
				tupla[0]=4; 
				tupla[1]=1; 
				break;
			case 90: 
				tupla[0]=10; 
				tupla[1]=1;
				break;
			case 165: 
				tupla[0]=8; 
				tupla[1]=1; 
				break;
			case 42: 
				tupla[0]=0; 
				tupla[1]=2;
				break;
			default:
				throw new Exception();
			}
		}catch (Exception e) {
				System.out.println("El carácter especificado no se encuentra disponible");
			}
		return tupla;
		
	}

}
