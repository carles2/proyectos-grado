import java.util.Random;

import javax.swing.JFrame;


public class Principal extends JFrame {
	//private Image fondoTablero;
	Tablero tablero;
	Jugador jugador1;
	Jugador jugador2;
	Bolsa bolsa;
	Diccionario diccionario;
	Random r;
	boolean salida;
	
	public Principal(){
		//fondoTablero = new ImageIcon("./resources/fondo.gif").getImage();
		super("Apalabrados V 0.1 Enrique J. Miguel y Jose Luis Urbano");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLayout(null);
		setSize(900, 650);
		


		diccionario = new Diccionario();
		r = new Random();
		bolsa = new Bolsa();

		tablero = new Tablero();
		add(tablero);
		tablero.repaint();
		jugador1 = new Jugador("Enrique", bolsa);
		jugador2 = new Jugador("Jose", bolsa);
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
				jugador1.pintaFichas();
				jugador1.realizaTurno(tablero, bolsa, diccionario);
				numeroTurnos1 = jugador1.getNumeroTurnosPasados();
				System.out.println("numero turnos1:" + numeroTurnos1);
				// pinta la puntuacion
				System.out.println("puntuacion del jugador "
						+ jugador1.getNombreJugador() + ": "
						+ jugador1.getPuntuacion() + " puntos");

				tablero.pintaTablero();
				tablero.repaint();

				jugador2.pintaFichas();
				jugador2.realizaTurno(tablero, bolsa, diccionario);
				numeroTurnos2 = jugador2.getNumeroTurnosPasados();
				System.out.println("numero turnos2:" + numeroTurnos2);

				// pinta la puntuacion
				System.out.println("puntuacion del jugador "
						+ jugador2.getNombreJugador() + ": "
						+ jugador2.getPuntuacion() + " puntos");

				tablero.pintaTablero();
				tablero.repaint();
				if ((!bolsa.getQuedanFichas()) || (numeroTurnos1 == 3)
						|| (numeroTurnos2 == 3))
					salida = true;
			} while (!salida);
		else
			do {
				jugador2.pintaFichas();
				jugador2.realizaTurno(tablero, bolsa, diccionario);
				numeroTurnos2 = jugador2.getNumeroTurnosPasados();
				System.out.println("numero turnos2:" + numeroTurnos2);
				// pinta la puntuacion
				System.out.println("puntuacion del jugador "
						+ jugador2.getNombreJugador() + ": "
						+ jugador2.getPuntuacion() + " puntos");

				tablero.pintaTablero();
				tablero.repaint();

				jugador1.pintaFichas();
				jugador1.realizaTurno(tablero, bolsa, diccionario);
				numeroTurnos1 = jugador1.getNumeroTurnosPasados();
				System.out.println("numero turnos1:" + numeroTurnos1);

				// pinta la puntuacion
				System.out.println("puntuacion del jugador "
						+ jugador1.getNombreJugador() + ": "
						+ jugador1.getPuntuacion() + " puntos");

				tablero.pintaTablero();
				tablero.repaint();
				if ((!bolsa.getQuedanFichas()) || (numeroTurnos2 == 3)
						|| (numeroTurnos1 == 3))
					salida = true;
			} while (!salida);

		System.out.print("fin del juego. ganador: ");
		if (jugador1.getPuntuacion() > jugador2.getPuntuacion())
			System.out.println(jugador1.getNombreJugador());
		else
			System.out.println(jugador2.getNombreJugador());
	}

}
