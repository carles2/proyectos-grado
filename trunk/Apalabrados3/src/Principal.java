import java.util.Random;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Font;
import java.awt.Panel;


public class Principal extends JFrame {
	//private Image fondoTablero;
	Tablero tablero;
	Jugador jugador1;
	Jugador jugador2;
	Bolsa bolsa;
	Diccionario diccionario;
	Random r;
	boolean salida;
	
	//objetos de diseño
	JPanel panel, panel_1, panel_2;
	Label label, label_1;
	TextField NombreJugador, PuntuacionJugador;
	
	public Principal(){
		//fondoTablero = new ImageIcon("./resources/fondo.gif").getImage();
		super("Apalabrados V 0.1 Enrique J. Miguel y Jose Luis Urbano");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLayout(null);
		setSize(1075, 575);
		getContentPane().setLayout(new GridLayout(1, 2, 0, 0));
		
		tablero = new Tablero();
		//panel = new JPanel();
		//tablero.setMaximumSize(new Dimension(526, 526));
		//tablero.setMinimumSize(new Dimension(526, 526));
		//panel.setMaximumSize(new Dimension(526, 526));
		//panel.setMinimumSize(new Dimension(526, 526));
		//getContentPane().add(panel);
		getContentPane().add(tablero);
		tablero.setLayout(null);
		tablero.repaint();
		
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		label = new Label("Turno del Jugador: ");
		label.setBounds(157, 10, 107, 22);
		panel.add(label);
		
		NombreJugador = new TextField();
		NombreJugador.setEditable(false);
		NombreJugador.setFont(new Font("Times New Roman", Font.BOLD, 24));
		NombreJugador.setBounds(265, 10, 172, 36);
		panel.add(NombreJugador);
		
		label_1 = new Label("Puntuacion del Jugador: ");
		label_1.setBounds(157, 60, 132, 22);
		panel.add(label_1);
		
		PuntuacionJugador = new TextField();
		PuntuacionJugador.setEditable(false);
		PuntuacionJugador.setBounds(382, 60, 55, 22);
		panel.add(PuntuacionJugador);
		
		/*panel_1 = new JPanel();
		panel_1.setBounds(70, 110, 370, 70);
		panel.add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBounds(70, 110, 370, 70);
		panel.add(panel_2);*/


		diccionario = new Diccionario();
		r = new Random();
		bolsa = new Bolsa();


		jugador1 = new Jugador("Enrique", bolsa);
		jugador2 = new Jugador("Jose", bolsa);
		jugador1.setBounds(70, 110, 370, 70);
		jugador2.setBounds(70, 110, 370, 70);
		panel.add(jugador1);
		jugador1.setLayout(null);
		panel.add(jugador2);
		jugador2.setLayout(null);
		repaint();
		setVisible(true);
		Juego();
	}
	
	public  void Juego(){



		int numeroTurnos1, numeroTurnos2;

		// realiza el juego mientras queden fichas
		tablero.pintaTablero();
		tablero.repaint();
		salida = false;

		if (r.nextFloat() >= 0.5)
			do {
				NombreJugador.setText(jugador1.getNombreJugador());
				PuntuacionJugador.setText(String.valueOf(jugador1.getPuntuacion()));
				muestraJugador(true);
				jugador1.pintaFichas();
				jugador1.repaint();
				jugador1.realizaTurno(tablero, bolsa, diccionario);
				numeroTurnos1 = jugador1.getNumeroTurnosPasados();
				System.out.println("numero turnos1:" + numeroTurnos1);
				// pinta la puntuacion
				/* System.out.println("puntuacion del jugador "
						+ jugador1.getNombreJugador() + ": "
						+ jugador1.getPuntuacion() + " puntos");
				*/

				
				tablero.pintaTablero();
				tablero.repaint();

				NombreJugador.setText(jugador2.getNombreJugador());
				PuntuacionJugador.setText(String.valueOf(jugador2.getPuntuacion()));
				muestraJugador(false);
				jugador2.pintaFichas();
				jugador2.repaint();
				jugador2.realizaTurno(tablero, bolsa, diccionario);
				numeroTurnos2 = jugador2.getNumeroTurnosPasados();
				System.out.println("numero turnos2:" + numeroTurnos2);

				// pinta la puntuacion
				/*System.out.println("puntuacion del jugador "
						+ jugador2.getNombreJugador() + ": "
						+ jugador2.getPuntuacion() + " puntos");
				*/

				
				tablero.pintaTablero();
				tablero.repaint();
				if ((!bolsa.getQuedanFichas()) || (numeroTurnos1 == 3)
						|| (numeroTurnos2 == 3))
					salida = true;
			} while (!salida);
		else
			do {
				NombreJugador.setText(jugador2.getNombreJugador());
				PuntuacionJugador.setText(String.valueOf(jugador2.getPuntuacion()));
				muestraJugador(false);
				jugador2.pintaFichas();
				jugador2.repaint();
				jugador2.realizaTurno(tablero, bolsa, diccionario);
				numeroTurnos2 = jugador2.getNumeroTurnosPasados();
				System.out.println("numero turnos2:" + numeroTurnos2);
				// pinta la puntuacion
				/*System.out.println("puntuacion del jugador "
						+ jugador2.getNombreJugador() + ": "
						+ jugador2.getPuntuacion() + " puntos");
				*/
				
				tablero.pintaTablero();
				tablero.repaint();

				NombreJugador.setText(jugador1.getNombreJugador());
				PuntuacionJugador.setText(String.valueOf(jugador1.getPuntuacion()));
				muestraJugador(true);
				jugador1.pintaFichas();
				jugador1.repaint();
				jugador1.realizaTurno(tablero, bolsa, diccionario);
				numeroTurnos1 = jugador1.getNumeroTurnosPasados();
				System.out.println("numero turnos1:" + numeroTurnos1);

				// pinta la puntuacion
				/*System.out.println("puntuacion del jugador "
						+ jugador1.getNombreJugador() + ": "
						+ jugador1.getPuntuacion() + " puntos");
				*/

				
				tablero.pintaTablero();
				tablero.repaint();
				if ((!bolsa.getQuedanFichas()) || (numeroTurnos2 == 3)
						|| (numeroTurnos1 == 3))
					salida = true;
			} while (!salida);
		/*
		System.out.print("fin del juego. ganador: ");
		if (jugador1.getPuntuacion() > jugador2.getPuntuacion())
			System.out.println(jugador1.getNombreJugador());
		else
			System.out.println(jugador2.getNombreJugador());
		*/

		if (jugador1.getPuntuacion() > jugador2.getPuntuacion()){
			JOptionPane.showMessageDialog(this," Fin del juego\n Ha ganado el jugdor" + jugador1.getNombreJugador() + " con una puntuacion de " + jugador1.getPuntuacion() +" puntos");
		}
		else{
			JOptionPane.showMessageDialog(this," Fin del juego\n Ha ganado el jugdor" + jugador2.getNombreJugador() + " con una puntuacion de " + jugador2.getPuntuacion() +" puntos");
		}
		
	}
	
	/**
	 * Establece que jugador es visible
	 * @param valor si es true muestra el jugador1 sino el jugador 2
	 */
	private void muestraJugador(boolean valor){
		jugador1.setVisible(valor);
		jugador2.setVisible(!valor);
	}
}
