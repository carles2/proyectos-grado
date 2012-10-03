import java.util.HashSet;
import java.util.Set;


public class PalabrasEncontradas {
		// crea el hash de palabras con las que ya se han jugado.
		// el valor inicial de la tabla es 50 palabras y el factor de carga es el 90%
		// es decir cuando llega al 90% el hashset crece
		private Set <Palabras> misPalabrasEncontradas = new HashSet <Palabras> (50,0.9f);


		/**
		 * 
		 * @param cadena, recibe como parametro la cadena a comprobar
		 * @return devuelve true si la cadena esta en la lista, false si no esta
		 */
		public boolean EstaInsertada(Palabras palabras){
			return misPalabrasEncontradas.contains(palabras);
		}
		
		/**
		 * 
		 * @param cadena, recibe como parametro la cadena a insertar
		 * @return devuelve true si la cadena ha sido insertada, false si ya estaba insertada.
		 */
		public boolean setInsertarPalabra(Palabras palabras){
			return misPalabrasEncontradas.add(palabras);
		}
		
		// metodo de control para comprobar que el hash no hay elemento duplicados. a borrar.
		public String toString(){
			return misPalabrasEncontradas.toString();
		}

}
