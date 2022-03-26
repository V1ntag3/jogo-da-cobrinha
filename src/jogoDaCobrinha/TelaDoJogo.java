package jogoDaCobrinha;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TelaDoJogo extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4;
	private static final int LARGURA_TELA = 1300;
	private static final int ALTURA_TELA = 750;
	private static final int TAMANHO_BLOCO = 30;
	private static final int UNIDADES = LARGURA_TELA * ALTURA_TELA / (TAMANHO_BLOCO * TAMANHO_BLOCO);
	private double intervalo = 200;
	private static final String NOME_FONTE = "Times";
	private  int[] eixoX = new int[UNIDADES];
	private  int[] eixoY = new int[UNIDADES];
	private int corpoCobra = 6;
	private int blocosComidos;
	private int blocoX;
	private int blocoY;
	public char direcao = 'D'; // C - Cima, B - Baixo, E - Esquerda, D - Direita
	private boolean estaRodando = false;
	private boolean reset = false;
	private boolean iniciar = false;
	Timer timer;
	Random random;

	TelaDoJogo() {
		random = new Random();
		setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(new LeitorDeTeclasAdapter());
		iniciarJogo();
	}

	public void mudarVelocidade(double valor) {
		timer.setDelay((int) valor);
		timer.restart();
	}

	public void iniciarJogo() {
		reset = false;
		blocosComidos = 0;
		corpoCobra = 6;
		Arrays.fill(eixoX, 0);
		Arrays.fill(eixoY, 0);
		direcao = 'D';
		criarBloco();
		estaRodando = true;
		timer = new Timer((int) intervalo, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		desenharTela(g);
	}

	public void desenharTela(Graphics g) {
		if (!iniciar) {
			telaInicial(g);
		} else {

			if (estaRodando) {
				g.setColor(Color.WHITE);
				g.fillRect(blocoX, blocoY, TAMANHO_BLOCO, TAMANHO_BLOCO);
				// g.fillOval(blocoX, blocoY, TAMANHO_BLOCO, TAMANHO_BLOCO);
				for (int i = 0; i < corpoCobra; i++) {
					g.setColor(Color.WHITE);
					g.fillRect(eixoX[i], eixoY[i], TAMANHO_BLOCO, TAMANHO_BLOCO);
				}
				g.setColor(Color.WHITE);
				g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
				FontMetrics metrics = getFontMetrics(g.getFont());
				g.drawString("Pontos: " + blocosComidos,
						(LARGURA_TELA - metrics.stringWidth("Pontos: " + blocosComidos)) / 2, g.getFont().getSize());
			} else {
				fimDeJogo(g);

			}
		}
	}

	private void criarBloco() {
		blocoX = random.nextInt(LARGURA_TELA / TAMANHO_BLOCO) * TAMANHO_BLOCO;
		blocoY = random.nextInt(ALTURA_TELA / TAMANHO_BLOCO) * TAMANHO_BLOCO;
	}

	public void fimDeJogo(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
		FontMetrics fontePontuacao = getFontMetrics(g.getFont());
		g.drawString("Pontos: " + blocosComidos,
				(LARGURA_TELA - fontePontuacao.stringWidth("Pontos: " + blocosComidos)) / 2, g.getFont().getSize());
		g.setColor(Color.WHITE);
		g.setFont(new Font(NOME_FONTE, Font.BOLD, 75));
		FontMetrics fonteFinal = getFontMetrics(g.getFont());
		g.drawString("Fim do Jogo", (LARGURA_TELA - fonteFinal.stringWidth("Fim do Jogo")) / 2, ALTURA_TELA / 2);
		g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
		FontMetrics fonteReset = getFontMetrics(g.getFont());
		g.drawString("Tecle ENTER para jogar novamente",
				(LARGURA_TELA - fonteReset.stringWidth("Tecle ENTER para jogar novamente")) / 2, ALTURA_TELA - 100 / 2);
	}

	public void telaInicial(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font(NOME_FONTE, Font.BOLD, 50));
		FontMetrics fonteIniciar = getFontMetrics(g.getFont());
		g.drawString("Tecle Enter Para Iniciar o Jogo",
				(LARGURA_TELA - fonteIniciar.stringWidth("Tecle Enter Para Iniciar o Jogo")) / 2, ALTURA_TELA / 2);
		g.setFont(new Font(NOME_FONTE, Font.BOLD, 30));
		FontMetrics fonteHardcore = getFontMetrics(g.getFont());
		g.drawString("Para ativar o modo HARDCORE tecle H",
				(LARGURA_TELA - fonteHardcore.stringWidth("Para ativar o modo HARDCORE tecle H")) / 2,
				ALTURA_TELA - 100 / 2);
	}

	public void actionPerformed(ActionEvent e) {
		if (estaRodando) {
			andar();
			alcancarBloco();
			validarLimites();
		} else {
			if (reset) {
				iniciarJogo();
			}
		}
		repaint();
	}

	private void andar() {
		for (int i = corpoCobra; i > 0; i--) {
			eixoX[i] = eixoX[i - 1];
			eixoY[i] = eixoY[i - 1];
		}
		switch (direcao) {
		case 'W':
			eixoY[0] = eixoY[0] - TAMANHO_BLOCO;
			break;
		case 'S':
			eixoY[0] = eixoY[0] + TAMANHO_BLOCO;
			break;
		case 'A':
			eixoX[0] = eixoX[0] - TAMANHO_BLOCO;
			break;
		case 'D':
			eixoX[0] = eixoX[0] + TAMANHO_BLOCO;
			break;
		default:
			break;
		}
	}

	private void alcancarBloco() {
		if (eixoX[0] == blocoX && eixoY[0] == blocoY) {
			corpoCobra++;
			blocosComidos++;
			intervalo = intervalo - (intervalo * 0.05);
			mudarVelocidade(intervalo);
			criarBloco();
		}
	}

	private void validarLimites() {
		// A cabeça bateu no corpo?
		for (int i = corpoCobra; i > 0; i--) {
			if (eixoX[0] == eixoX[i] && eixoY[0] == eixoY[i]) {
				estaRodando = false;
				break;
			}
		}
		// A cabeça tocou uma das bordas Direita ou esquerda?
		if (eixoX[0] < 0 || eixoX[0] > LARGURA_TELA) {
			estaRodando = false;
		}
		// A cabeça tocou o piso ou o teto?
		if (eixoY[0] < 0 || eixoY[0] > ALTURA_TELA) {
			estaRodando = false;
		}
	}

	public class LeitorDeTeclasAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				if (direcao != 'D') {
					direcao = 'A';
				}
				break;
			case KeyEvent.VK_D:
				if (direcao != 'A') {
					direcao = 'D';
				}
				break;
			case KeyEvent.VK_W:
				if (direcao != 'S') {
					direcao = 'W';
				}
				break;
			case KeyEvent.VK_S:
				if (direcao != 'W') {
					direcao = 'S';
				}
				break;
			case KeyEvent.VK_ENTER:
				if (iniciar == false) {
					iniciar = true;
					reset = false;
				} else {
					if (estaRodando == false) {
						reset = true;
					}
				}
			default:
				break;
			}
		}
	}

}
