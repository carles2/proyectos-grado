public class Ficha {
	private char letra;
	private int valor;

	Ficha(char letra, int valor) {
		this.letra = letra;
		this.valor = valor;
	}

	Ficha() {
	}

	/**
	 * @return el letra
	 */
	public char getLetra() {
		return letra;
	}

	/**
	 * @param letra
	 *            el letra a establecer
	 */
	public void setLetra(char letra) {
		this.letra = letra;
	}

	/**
	 * @return el valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            el valor a establecer
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

}
