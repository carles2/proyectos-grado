import java.util.HashSet;
import java.util.Set;


public class PalabrasEncontradas {
		// crea el hash de palabras con las que ya se han jugado.
		// el valor inicial de la tabla es 50 palabras y el factor de carga es el 90%
		// es decir cuando llega al 90% el hashset crece
		private Set <String> misPalabrasEncontradas = new HashSet <String> (50,0.9f);

		/**
		 * 
		 * @param cadena, recibe como parametro la cadena a comprobar
		 * @return devuelve true si la cadena esta en la lista, false si no esta
		 */
		public boolean EstaInsertada(String cadena){
			return misPalabrasEncontradas.contains(cadena);
		}
		
		/**
		 * 
		 * @param cadena, recibe como parametro la cadena a insertar
		 * @return devuelve true si la cadena ha sido insertada, false si ya estaba insertada.
		 */
		public boolean setInsertarPalabra(String cadena){
			return misPalabrasEncontradas.add(cadena);
		}
		
		// metodo de control para comprobar que el hash no hay elemento duplicados. a borrar.
		public String toString(){
			return misPalabrasEncontradas.toString();
		}

}
