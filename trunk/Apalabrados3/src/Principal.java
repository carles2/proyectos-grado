import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Image;

import java.awt.GridLayout;
import javax.swing.JPanel;

import java.awt.Label;
import java.awt.TextField;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

/**
 * @author Enrique J Miguel Calvo y José Luis Urbano
 * 
 */
@SuppressWarnings("serial")
public class Principal extends JFrame {
	private Tablero tablero;
	private Jugador jugador1;
	private Jugador jugador2;
	private Bolsa bolsa;
	private PalabrasEncontradas palabras;
	private Diccionario diccionario;
	private Random r;
	private boolean moviendoFicha = false;
	private boolean jugado = false;
	private boolean validado = false;
	private int FichaSeleccionada = -1;
	private boolean imagenIniciada = false;
	private Image miImagen;

	private FichasValidas fichasValidas;

	// objetos de diseño
	private JPanel panel;
	private Label label, label_1;
	private TextField NombreJugador, PuntuacionJugador;
	private JButton btnDevolverFichas, btnValidar, btnPasarTruno;

	/**
	 * 
	 */
	public Principal() {
		super("Apalabrados V 0.3 Enrique J. Miguel y Jose Luis Urbano");

		getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (moviendoFicha) {
					mueveImagen(e.getX(), e.getY());
				}

			}
		});
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x, y, xi, yi, xf, yf;

				int inicioX = 611;
				int inicioY = 125;

				boolean encontrado = false;
				boolean[] FichaNoUsadas;

				if (moviendoFicha == false) { //

					x = e.getX();
					y = e.getY();
					if (jugador1.isTurno()) { // si es turno del jugador 1 cojo
												// sus fichas sino las del otro
						FichaNoUsadas = jugador1.getFichasNoUsadas();
					} else {
						FichaNoUsadas = jugador2.getFichasNoUsadas();
					}

					for (int i = 0; ((i < 7) && (encontrado == false)); i++) {
						if (FichaNoUsadas[i]) {
							xi = inicioX + 50 * i;
							yi = inicioY;
							xf = xi + 35;
							yf = yi + 35;

							if ((x >= xi && x <= xf) && (y >= yi && y <= yf)) {
								encontrado = true;
								FichaSeleccionada = i;
								moviendoFicha = true;
							}
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (moviendoFicha) {
					int x, y;
					Casilla miCasilla;
					x = e.getX();
					y = e.getY();
					if ((x <= 526) && (y <= 526)) {
						x = (int) Math.ceil(x / 35); // obetengo las coordenada
														// x e y respecto a las
														// fichas del tablero
						y = (int) Math.ceil(y / 35);
						if (tablero.getCasilla(x, y).isVacio()) {
							miCasilla = tablero.getCasilla(x, y);
							miCasilla.setPosicionX(x);
							miCasilla.setPosicionY(y);

							if (jugador1.isTurno()) { // si es turno del jugador
														// 1 cojo sus fichas
														// sino las del otro
								miCasilla.setFicha(jugador1
										.getFichaPosicion(FichaSeleccionada));
							} else {
								miCasilla.setFicha(jugador2
										.getFichaPosicion(FichaSeleccionada));
							}

							/*
							 * //buscar si alrededor hay algun comodin
							 * hayComodin(miCasilla);
							 */
							if (fichasValidas.add(miCasilla)) {
								moviendoFicha = false;
								miCasilla.setVacio(false);
								tablero.setCasilla(miCasilla);
							} else {
								if (jugador1.isTurno()) { // si es turno del
															// jugador 1 cojo
															// sus fichas sino
															// las del otro
									jugador1.setFichaPosicion(FichaSeleccionada);
								} else {
									jugador2.setFichaPosicion(FichaSeleccionada);
								}
								miCasilla.setFicha(null);
								mensaje("Has de colocar la ficha al lado de una existente");
							}

							imagenIniciada = false;

							jugado = true;
							repaint();
						}
					}

				}
			}
		});
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(1075, 575);
		getContentPane().setLayout(new GridLayout(1, 2, 0, 0));

		tablero = new Tablero();
		getContentPane().add(tablero);
		tablero.setLayout(null);

		fichasValidas = new FichasValidas(tablero);

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

		palabras = new PalabrasEncontradas();
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

		btnValidar = new JButton("Validar");
		btnValidar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validado = true;
				validar();
				if (jugador1.isTurno()) {
					PuntuacionJugador.setText(String.valueOf(jugador1
							.getPuntuacion()));
				} else {
					PuntuacionJugador.setText(String.valueOf(jugador2
							.getPuntuacion()));
				}
				repaint();
			}
		});
		btnValidar.setBounds(70, 252, 89, 23);
		panel.add(btnValidar);

		btnPasarTruno = new JButton("Pasar Truno");
		btnPasarTruno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pasaTurno();
			}
		});
		btnPasarTruno.setBounds(330, 252, 107, 23);
		panel.add(btnPasarTruno);

		btnDevolverFichas = new JButton("Devolver Fichas");
		btnDevolverFichas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jugado) {// solo puedo devolver las fichas si no he jugado
								// con ellas
					if (jugador1.isTurno()) {
						jugador1.devolverFichas();
					} else {
						jugador2.devolverFichas();
					}
				} else {
					mensaje("No puedes devolver las fichas, ya has usado alguna");
				}
			}
		});
		btnDevolverFichas.setBounds(182, 252, 132, 23);
		panel.add(btnDevolverFichas);
		setVisible(true);
		if (r.nextFloat() >= 0.5) {
			turno(true);
		} else {
			turno(false);
		}

		Juego();
	}

	private void devolverFichasErroneas() {
		Casilla casilla;
		int x, y;
		int identificador;
		Ficha[] casillajugador;
		boolean encontrada;
		if (jugador1.isTurno()) {
			casillajugador = jugador1.getLasFichas();
		} else {
			casillajugador = jugador2.getLasFichas();
		}
		if (!fichasValidas.isEmpty()) {
			do {
				casilla = fichasValidas.remove(0);
				x = casilla.getPosicionX();
				y = casilla.getPosicionY();
				identificador = casilla.getFicha().getIdentificador();
				if (!tablero.getCasilla(x, y).isValidada()) {
					encontrada = false;
					for (int i = 0; ((i < 7) && (!encontrada)); i++) {
						if (casillajugador[i].getIdentificador() == identificador) {
							encontrada = true;
							if (jugador1.isTurno()) {
								jugador1.setFichaPosicion(i);
							} else {
								jugador2.setFichaPosicion(i);
							}
							tablero.getCasilla(x, y).setFicha(null);
							tablero.getCasilla(x, y).setValidada(false);
							tablero.getCasilla(x, y).setVacio(true);
						}
					}
				}
			} while (!fichasValidas.isEmpty());
		}
		tablero.getCasilla(7, 7).getFicha().setLetra('*');
		bolsa.restableceComodin();
	}

	private void Juego() {
		if (jugador1.isTurno()) {
			NombreJugador.setText(jugador1.getNombreJugador());
			PuntuacionJugador.setText(String.valueOf(jugador1.getPuntuacion()));
			muestraJugador(true);
			jugador1.repaint();
		} else {
			NombreJugador.setText(jugador2.getNombreJugador());
			PuntuacionJugador.setText(String.valueOf(jugador2.getPuntuacion()));
			muestraJugador(false);
			jugador2.repaint();
		}
	}

	private void mensaje(String cadena) {
		JOptionPane.showMessageDialog(this, cadena);
	}

	/**
	 * Establece que jugador es visible
	 * 
	 * @param valor
	 *            si es true muestra el jugador1 sino el jugador 2
	 */
	private void muestraJugador(boolean valor) {
		jugador1.setVisible(valor);
		jugador2.setVisible(!valor);
	}

	private void mueveImagen(int x, int y) {
		if (!imagenIniciada) {
			if (jugador1.isTurno()) {
				miImagen = jugador1.getFichaPosicion(FichaSeleccionada)
						.getImagen();
			} else {
				miImagen = jugador2.getFichaPosicion(FichaSeleccionada)
						.getImagen();
			}
			imagenIniciada = true;
		}
		this.getContentPane().getGraphics()
				.drawImage(miImagen, x - 15, y - 15, this);
		// repaint();
	}

	private void pasaTurno() {
		int numeroTurnos1 = 0;
		int numeroTurnos2 = 0;
		if (!jugado) {
			if (jugador1.isTurno()) {
				jugador1.incrementaNumeroTurnosJugados();
			} else {
				jugador2.incrementaNumeroTurnosJugados();
			}

		} else {
			if (validado) {
				validado = false;
				jugado = false;
				if (jugador1.isTurno()) {
					jugador1.setNumeroTurnosJugados0();
					jugador1.realizaTurno();
				} else {
					jugador2.setNumeroTurnosJugados0();
					jugador2.realizaTurno();
				}
				if (jugador1.isTurno()) {
					numeroTurnos1 = jugador1.getNumeroTurnosPasados();
				} else {
					numeroTurnos2 = jugador2.getNumeroTurnosPasados();
				}

				if ((!bolsa.getQuedanFichas()) || (numeroTurnos2 == 3)
						|| (numeroTurnos1 == 3)) {
					// fin de juego ocultar las cosas
					if (jugador1.getPuntuacion() == jugador2.getPuntuacion()) {
						if (jugador1.isTurno()) {
							mensaje(" Fin del juego\n Ha ganado el jugdor"
									+ jugador1.getNombreJugador()
									+ " con una puntuacion de "
									+ jugador1.getPuntuacion() + " puntos");
						} else {
							mensaje(" Fin del juego\n Ha ganado el jugdor"
									+ jugador2.getNombreJugador()
									+ " con una puntuacion de "
									+ jugador2.getPuntuacion() + " puntos");
						}
					} else {
						if (jugador1.getPuntuacion() > jugador2.getPuntuacion()) {
							mensaje(" Fin del juego\n Ha ganado el jugdor"
									+ jugador1.getNombreJugador()
									+ " con una puntuacion de "
									+ jugador1.getPuntuacion() + " puntos");
						} else {
							mensaje(" Fin del juego\n Ha ganado el jugdor"
									+ jugador2.getNombreJugador()
									+ " con una puntuacion de "
									+ jugador2.getPuntuacion() + " puntos");
						}
					}
					//
					if (numeroTurnos1 == 3) {
						mensaje(" Fin del juego\n Ha ganado el jugdor"
								+ jugador2.getNombreJugador()
								+ " con una puntuacion de "
								+ jugador2.getPuntuacion() + " puntos");
					}
					if (numeroTurnos2 == 3) {
						mensaje(" Fin del juego\n Ha ganado el jugdor"
								+ jugador1.getNombreJugador()
								+ " con una puntuacion de "
								+ jugador2.getPuntuacion() + " puntos");
					}
					// / a borrar cuadno este todo bien
					jugador1.setNumeroTurnosJugados0();
					jugador2.setNumeroTurnosJugados0();
					// fin del juego solo queda dar a la x
				}
				turno(!jugador1.isTurno());
				Juego();
			} else {
				mensaje("Has de validar las fichas usadas para continuar");
			}
		}
	}

	private void turno(boolean valor) {
		jugador1.setTurno(valor);
		jugador2.setTurno(!valor);
	}

	private void validar() {
		Thread[] h = new Thread[15];
		Thread[] v = new Thread[15];
		Puntuacion p = new Puntuacion();

		for (int i = 0; i < 15; i++) {
			h[i] = new BusquedaPalabras(diccionario, tablero, palabras, i,
					true, p);
			h[i].start();
			v[i] = new BusquedaPalabras(diccionario, tablero, palabras, i,
					false, p);
			v[i].start();
		}

		for (int i = 0; i < 15; i++) {
			try {
				h[i].join();
				v[i].join();
			} catch (InterruptedException e) {

			} catch (Exception e) {
			}

		}
		if (jugador1.isTurno()) {
			jugador1.setPuntuacion(p.getPuntuacion());
		} else {
			jugador2.setPuntuacion(p.getPuntuacion());
		}

		devolverFichasErroneas();
	}
}
