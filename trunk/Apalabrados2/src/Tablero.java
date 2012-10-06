 
public class Tablero {
	
	private Casilla[][] elTablero; 

	Tablero(){

		elTablero = new Casilla[15][];
	    for (int i=0; i<15; i++){
		   //asigno en memoria para cada fila
		   elTablero[i]=new Casilla[15];
		   for(int j=0; j<15; j++){
		      //ahora creo un objeto para cada elemento
		      elTablero[i][j]=new Casilla();
	    	}
	    }
	    inicializa();
	}
	
	/**
	 * Inicializa la matriz con los valores de las casillas especiales, 
	 * las del tipo (x,y,1) son de doble letra
	 * las del tipo (x,y,2) son de triple letra
	 * las del tipo (x,y,3) son de doble palabra
	 * las del tipo (x,y,4) son de triple palabra
	 */
	private void inicializa(){
		int matriz[][]={
				{2,2,1},{12,2,1},{6,4,1},{8,4,1},{4,6,1},{10,6,1},{4,8,1},{10,8,1},{6,10,1},{8,10,1},{2,12,1},{12,12,1},
				
				{4,0,2},{10,0,2},{1,1,2},{13,1,2},{6,2,2},{8,2,2},{3,3,2},{11,3,2},
				{0,4,2},{14,4,2},{5,5,2},{9,5,2},{2,6,2},{12,6,2},{2,8,2},{12,8,2},
				{5,9,2},{9,9,2},{0,10,2},{14,10,2},{3,11,2},{11,11,2},{6,12,2},{8,12,2},
				{1,13,2},{13,13,2},{4,14,2},{10,14,2},
				
				{5,1,3},{9,1,3},{7,3,3},{1,5,3},{13,5,3},{1,9,3},{13,9,3},{7,11,3},{5,13,3},{9,13,3},
				
				{0,2,4},{0,12,4},{2,0,4},{2,14,4},{12,0,4},{12,14,4},{14,2,4},{14,12,4}
		};
		for (int i=0;i<matriz.length;i++){
			//establecemos la casilla x,y del tipo especial
			elTablero[matriz[i][0]][matriz[i][1]].setEspecial(true);
			elTablero[matriz[i][0]][matriz[i][1]].setPosicionX(matriz[i][0]);
			elTablero[matriz[i][0]][matriz[i][1]].setPosicionY(matriz[i][1]);
			// segun el tercer parametro del vector establecemos el tipo de casilla especial.
			switch (matriz[i][2]){
				case 1: elTablero[matriz[i][0]][matriz[i][1]].setTCasilla(Datos.TipoCasilla.DL);break;
				case 2: elTablero[matriz[i][0]][matriz[i][1]].setTCasilla(Datos.TipoCasilla.TL);break;
				case 3: elTablero[matriz[i][0]][matriz[i][1]].setTCasilla(Datos.TipoCasilla.DP);break;
				case 4: elTablero[matriz[i][0]][matriz[i][1]].setTCasilla(Datos.TipoCasilla.TP);break;
			}
	
		}
		elTablero[7][7].setVacio(false);
		elTablero[7][7].setLetra('*');
		elTablero[7][7].setValor(0);
		elTablero[7][7].setPosicionX(7);
		elTablero[7][7].setPosicionY(7);
	}
	
	/**
	 * 
	 * @return devuelve el tablero de casillas actual
	 */
	public Casilla[][] getTablero(){
		return elTablero;
	}
	
	/**
	 *  
	 * @param Coordenada X
	 * @param Coordenada Y
	 * @return Devuelve la casilla del tablero de las cordenadas X,Y
	 */
	public Casilla getCasilla(int posx, int posy){
		return elTablero[posx][posy];
	}
	
	/**
	 * 
	 * @param Guarda una casilla en el tablero en la posicon de las coordenada de casilla
	 */
	public void setCasilla(Casilla casilla){
		elTablero[casilla.getPosicionX()][casilla.getPosicionY()]=casilla;
	}
	

	 /**
	 * Funcion que pinta el tablero en la salida estandar
	 * el valor de la letra no se me ocurre como hacerlo aun.
	 */
	public void pintaTablero(){
		System.out.println("-------------------------------------------------------------");
		for (int i=0; i<15; i++){

			for(int j=0;j<15;j++){
				System.out.print("|");
				if (elTablero[j][i].isEspecial()) System.out.print(elTablero[j][i].getTCasilla()+" ");
				else if (elTablero[j][i].isVacio()) System.out.print("   ");
					 else System.out.print(" " + elTablero[j][i].getLetra()+" ");

			}
			System.out.println("|");
			System.out.println("-------------------------------------------------------------");
			
		}
	}
	
}

