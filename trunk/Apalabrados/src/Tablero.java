 
public class Tablero {
	
	Casilla[][] elTablero = new Casilla[15][];
			
	Tablero(){
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
	
	private void inicializa(){
		int matriz[][]={
				{2,2,1},{12,2,1},{6,4,1},{8,4,1},{4,6,1},{10,6,1},{4,8,1},{10,8,1},{6,10,1},{8,10,1},{2,12,1},{12,12,1},
				
				{4,0,2},{10,0,2},{1,1,2},{13,1,2},{6,2,2},{8,2,2},{3,3,2},{11,3,2},{7,7,2},
				{0,4,2},{14,4,2},{5,5,2},{9,5,2},{2,6,2},{12,6,2},{2,8,2},{12,8,2},
				{5,9,2},{9,9,2},{0,10,2},{14,10,2},{3,11,2},{11,11,2},{6,12,2},{8,12,2},
				{1,13,2},{13,13,2},{4,14,2},{10,14,2},
				
				{5,1,3},{9,1,3},{7,3,3},{1,5,3},{13,5,3},{1,9,3},{13,9,3},{7,11,3},{5,13,3},{9,13,3},
				
				{0,2,4},{0,12,4},{2,0,4},{2,14,4},{12,0,4},{12,14,4},{14,2,4},{14,12,4}
		};
		for (int i=0;i<matriz.length;i++){
			elTablero[matriz[i][0]][matriz[i][1]].setEspecial(true);
			switch (matriz[i][2]){
				case 1: elTablero[matriz[i][0]][matriz[i][1]].setTCasilla(TipoCasilla.DL);break;
				case 2: elTablero[matriz[i][0]][matriz[i][1]].setTCasilla(TipoCasilla.TL);break;
				case 3: elTablero[matriz[i][0]][matriz[i][1]].setTCasilla(TipoCasilla.DP);break;
				case 4: elTablero[matriz[i][0]][matriz[i][1]].setTCasilla(TipoCasilla.TP);break;
			}
	
		}
	}
	
	public void pintaTablero(){
		System.out.println("-------------------------------------------------------------");
		for (int i=0; i<15; i++){

			for(int j=0;j<15;j++){
				System.out.print("|");
				if (elTablero[j][i].isEspecial()) System.out.print(elTablero[j][i].getTCasilla()+" ");
				else System.out.print("   ");

			}
			System.out.println("|");
			System.out.println("-------------------------------------------------------------");
			
		}
	}
}
