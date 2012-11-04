import java.awt.Image;

import javax.swing.ImageIcon;

public class Ficha {
	private char letra;
	private int valor;
	private Image imagen;

	Ficha(char letra, int valor,String recurso) {
		this.letra = letra;
		this.valor = valor;
		this.imagen= new ImageIcon(recurso).getImage();
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
	
	/**
	 * Devuelve la imagen asociada a esa ficha
	 * @return la imagen de la ficha
	 */
	public Image getImagen(){
		return imagen;
	}

}
