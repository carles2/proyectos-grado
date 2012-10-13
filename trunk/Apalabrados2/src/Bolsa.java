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
		 *  8 puntos: J*1, �*1, X*1 
		 *  10 puntos: Z*1 no esta la K ni la W, pero se podrian a�adir a la de 10 puntos
		 */

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
		
		/*
		int abecedario[][] = { 
				
				{ 65, 1, 12 }, // Letra A, Valor de A, numero de A
				{ 66, 3, 2 }, // Letra B
				{ 67, 3, 4 }, // Letra C
				{ 68, 2, 5 }, // Letra D
				{ 69, 1, 12 }, // Letra E
				{ 70, 4, 1 }, // Letra F
				{ 71, 2, 2 }, // Letra G
				{ 72, 4, 2 }, // Letra H
				{ 73, 1, 6 }, // Letra I
				{ 74, 8, 1 }, // Letra J
				{ 76, 1, 4 }, // Letra L
				{ 77, 3, 2 }, // Letra M
				{ 78, 1, 5 }, // Letra N
				{ 165, 8, 1 }, // Letra �
				{ 79, 1, 9 }, // Letra O
				{ 80, 3, 2 }, // Letra P
				{ 81, 5, 1 }, // Letra Q
				{ 82, 1, 5 }, // Letra R
				{ 83, 1, 6 }, // Letra S
				{ 84, 1, 4 }, // Letra T
				{ 85, 1, 5 }, // Letra U
				{ 86, 4, 1 }, // Letra V
				{ 88, 8, 1 }, // Letra X
				{ 89, 4, 1 }, // Letra Y
				{ 90, 10, 1 }, // Letra Z
				{ 42, 0, 2 } // Comodin *
		};

*/
		usado = new boolean[97];
		numeroFichas = 97;
		miBolsa = new Ficha[97];
		int aux = 0;
		int[] tuplaAux=new int[2];
		int tope;
		int iteAux=0;
		for (int i = 0; i < 97; i++)
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
		ModificarBolsa('A',0);
		System.out.println(miBolsa.length);
		MostrarBolsa();
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
		if (numeroFichas != 0)
			return true;
		else
			return false;
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

	/**
	 * Modifica la cantidad de fichas en la bolsa dado el caracter
	 * @param caracter Caracter del que modificar su cantidad
	 * @param cantidadNueva Nueva cantidad de fichas con ese caracter
	 */
	private void ModificarBolsa(char caracter,int cantidadNueva)
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
	
	public Ficha[] getMiBolsa() {
		return miBolsa;
	}

	public void setMiBolsa(Ficha[] miBolsa) {
		this.miBolsa = miBolsa;
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
