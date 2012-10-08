import java.util.Random;

public class Bolsa {
	
	private int numeroFichas;
	private Random r;
	private Ficha[] miBolsa;
	private boolean [] usado;
	
	Bolsa(){
		
		/** 
		 * distribucion de letras segun el scrabbel original, a modificar segun la distribucion de apalabrados
		 * 0 puntos: dos comodines
		 * 1 punto: A*12, E*12, I*6, L*4, N*5, O*9, R*5, S*6, T*4, U*5, 
		 * 2 puntos: D*5, G*2 
		 * 3 puntos: B*2, C*4, M*2, P*2
		 * 4 puntos: F*1, H*2, V*1, Y*1
		 * 5 putnos: Q*1
		 * 8 puntos: J*1, ï¿½*1, X*1
		 * 10 puntos: Z*1
		 * no esta la K ni la W, pero se podrian aï¿½adir a la de 10 puntos
		 */

	/*	int abecedario[][]={{65,1},{65,1},{65,1},{65,1},{65,1},{65,1},{65,1},{65,1},{65,1},{65,1},
						{65,1},{65,1},{66,3},{66,3},{67,3},{67,3},{67,3},{67,3},{68,2},{68,2},
						{68,2},{68,2},{68,2},{69,1},{69,1},{69,1},{69,1},{69,1},{69,1},{69,1},
						{69,1},{69,1},{69,1},{69,1},{69,1},{70,4},{71,2},{71,2},{72,4},{72,4},
						{73,1},{73,1},{73,1},{73,1},{73,1},{73,1},{74,8},{76,1},{76,1},{76,1},
						{76,1},{77,3},{77,3},{78,1},{78,1},{78,1},{78,1},{78,1},{165,8},{79,1},
						{79,1},{79,1},{79,1},{79,1},{79,1},{79,1},{79,1},{79,1},{80,3},{80,3},
						{81,5},{82,1},{82,1},{82,1},{82,1},{82,1},{83,1},{83,1},{83,1},{83,1},
						{83,1},{83,1},{84,1},{84,1},{84,1},{84,1},{85,1},{85,1},{85,1},{85,1},
						{85,1},{86,4},{88,8},{89,4},{90,10},{42,0},{42,0}
		};*/

		int abecedario[][]={{65,1,12}, // Letra A, Valor de A, numero de A
			{66,3,2}, //Letra B
			{67,3,4}, //Letra C
			{68,2,5}, //Letra D
			{69,1,12}, //Letra E
			{70,4,1}, //Letra F
			{71,2,2}, //Letra G
			{72,4,2}, // Letra H
			{73,1,6}, //Letra I
			{74,8,1}, //Letra J
			{76,1,4}, //Letra L
			{77,3,2}, //Letra M
			{78,1,5}, //Letra N
			{165,8,1}, //Letra Ñ
			{79,1,9}, //Letra O
			{80,3,2}, // Letra P
			{81,5,1}, //Letra Q
			{82,1,5}, //Letra R
			{83,1,6}, //Letra S
			{84,1,4}, //Letra T
			{85,1,5}, //Letra U
			{86,4,1}, //Letra V
			{88,8,1}, //Letra X
			{89,4,1}, //Letra Y
			{90,10,1}, //Letra Z
			{42,0,2}   // Comodin *
		};
		
		usado= new boolean[97];
		numeroFichas=97;
		miBolsa = new Ficha[97];
		r=new Random();
		int aux=0;
		for (int i=0;i<97;i++) usado[i]=false; // control si se usa o no una variable.
		for (int i=0;i<abecedario.length;i++){
			for (int j=0;j<abecedario[i][2];j++){
				miBolsa[aux] = new Ficha((char)abecedario[i][0],abecedario[i][1]);
				aux++;
			}
		}
	}
	
	/**
	 * sacamos una ficha de forma aleatoria de la bolsa y cuando la sacamos la marcamos
	 * con el setvacio para no volver a sacar esa ficha.
	 * solo sacamos fichas si quedan fichas que sacar, si no hay fichas devolvemos null
	 * @return Devolvemos la casilla generada de la bolsa
	 */
	public Ficha getFicha(){
		// si no hay fichas no entramos ya que se quedaria el do while en ciclo infinito.
		if (numeroFichas==0) return null;
		else{
			int numero;
			do{
				numero=r.nextInt(97);
			}while (usado[numero]);
			// al sacar una ficha la marcamos que ya no esta vacia con lo 
			// que nos sirve para saber si ha sido sacada o no
			usado[numero]=true;
			// restamos 1 a las fichas que quedan en la bolsa
			numeroFichas--;
			//return miCasilla;
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
	
	public boolean getQuedanFichas(){
		if (numeroFichas!=0) return true;
		else return false;
	}

}
