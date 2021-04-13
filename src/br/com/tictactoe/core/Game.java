package br.com.tictactoe.core;

import java.io.IOException;

import br.com.tictactoe.constants.Constants;
import br.com.tictactoe.score.FileScoreManager;
import br.com.tictactoe.score.ScoreManager;
import br.com.tictactoe.ui.UI;

public class Game {

	private Board board = new Board();
	private Player[] players = new Player[Constants.SYMBOL_PLAYERS.length];
	private int currentPlayerIndex = -1;
	private ScoreManager scoreManager;

	public void play() {
		try {

			scoreManager = createScoreManager();

			UI.printGameTitle();

			createPlayers();

			while (true) {

				boolean gameEnded = false;
				Player currentPlayer = nextPlayer();
				Player winner = null;
				board.clear();
				while (!gameEnded) {
					board.print();
					boolean sequenceFound;
					try {
						sequenceFound = currentPlayer.play();
					} catch (Exception e) {
						UI.printText("ERRO: " + e.getMessage());
						continue;
					}

					if (sequenceFound) {
						gameEnded = true;
						winner = currentPlayer;
					} else if (board.isFull()) {
						gameEnded = true;
					} else {
						currentPlayer = nextPlayer();
					}
				}

				board.print();
				UI.printNewLine();
				if (winner == null) {
					UI.printText("O jogo terminou empatado");
				} else {
					UI.printText("O jogador '" + winner.getNome() + "' venceu o jogo!");
					board.print();
					scoreManager.saveScore(winner);
				}
				
				UI.printText("Fim do jogo!");
				String resp = UI.readInput("Deseja jogar mais uma vez? [S/N]");
				if(resp.equalsIgnoreCase("N")) {
					for (Player player : players) {
						int score = scoreManager.getScore(player);
						UI.printText("O " + player.getNome() + " venceu " + score + " vez(s)");
					}
					UI.printText("Volte sempre!");
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Player createPlayer(int index) {
		String name = UI.readInput("Jogador " + (index + 1) + " => ");
		char symbol = Constants.SYMBOL_PLAYERS[index];
		Player player = new Player(name, board, symbol);

		Integer score = scoreManager.getScore(player);
		if (score != null) {
			UI.printText("O jogador '" + player.getNome() + "' já possui " + score + " vitória(s)!");
		}

		UI.printText("O jogador '" + name + "' vai usar o símbolo '" + symbol + "'");
		return player;

	}

	private Player nextPlayer() {
		/*
		 * if(currentPlayerIndex >= (players.length - 1)) { currentPlayerIndex = 0;
		 * return players[currentPlayerIndex]; }
		 * 
		 * return players[++currentPlayerIndex];
		 */
		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
		return players[currentPlayerIndex];
	}

	private ScoreManager createScoreManager() throws IOException {
		return new FileScoreManager();
	}
	
	private void createPlayers() {
		for (int i = 0; i < players.length; i++) {
			players[i] = createPlayer(i);
		}
	}
}
