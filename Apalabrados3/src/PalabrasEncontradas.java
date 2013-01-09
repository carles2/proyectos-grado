import java.util.HashSet;
import java.util.Set;

/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
public class PalabrasEncontradas {
	// crea el hash de palabras con las que ya se han jugado.
	// el valor inicial de la tabla es 50 palabras y el factor de carga es el
	// 90%
	// es decir cuando llega al 90% el hashset crece
	private Set<PalabrasIntroducidas> misPalabrasEncontradas = new HashSet<PalabrasIntroducidas>(
			200, 0.9f);

	/**
	 * 
	 * @param palabra
	 * @param cadena
	 *            , recibe como parametro la cadena a comprobar
	 * @return devuelve true si la cadena esta en la lista, false si no esta
	 */
	public synchronized boolean EstaInsertada(PalabrasIntroducidas palabra) {
		return misPalabrasEncontradas.contains(palabra);
	}

	/**
	 * 
	 * @param palabra
	 * @param cadena
	 *            , recibe como parametro la cadena a insertar
	 * @return devuelve true si la cadena ha sido insertada, false si ya estaba
	 *         insertada.
	 */
	public synchronized boolean setInsertarPalabra(PalabrasIntroducidas palabra) {
		return misPalabrasEncontradas.add(palabra);
	}

	// metodo de control para comprobar que el hash no hay elemento duplicados.
	// a borrar.
	@Override
	public synchronized String toString() {
		Object[] array;
		PalabrasIntroducidas palabras;
		String cadena = "[";
		array = misPalabrasEncontradas.toArray();
		for (int i = 0; i < array.length; i++) {
			palabras = (PalabrasIntroducidas) array[i];
			cadena = cadena + palabras.toString() + "\n";
		}
		cadena = cadena + " palabras Introducidas: " + array.length + "]";
		return cadena;
	}
}
