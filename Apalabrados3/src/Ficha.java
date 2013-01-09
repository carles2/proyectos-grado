import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
public class Ficha {
	private char letra;
	private int valor;
	private Image imagen;
	private int identificador;

	Ficha() {
	}

	Ficha(char letra, int valor, int identificador, String recurso) {
		this.letra = letra;
		this.valor = valor;
		this.identificador = identificador;
		this.imagen = new ImageIcon(recurso).getImage();
	}

	/**
	 * Devuelve el identificador unico de la ficha
	 * 
	 * @return el identificador de la ficha
	 */
	public int getIdentificador() {
		return identificador;
	}

	/**
	 * Devuelve la imagen asociada a esa ficha
	 * 
	 * @return la imagen de la ficha
	 */
	public Image getImagen() {
		return imagen;
	}

	/**
	 * @return el letra
	 */
	public char getLetra() {
		return letra;
	}

	/**
	 * @return el valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @param letra
	 *            el letra a establecer
	 */
	public void setLetra(char letra) {
		this.letra = letra;
	}

	/**
	 * @param valor
	 *            el valor a establecer
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
}
