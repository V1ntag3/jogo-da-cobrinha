package jogoDaCobrinha;

import javax.swing.*;

public class IniciarJogo extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new IniciarJogo();
	}

	IniciarJogo() {
		add(new TelaDoJogo());
		// add(new TelaIniciar());
		setTitle("Jogo da Cobrinha - Snake game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
}