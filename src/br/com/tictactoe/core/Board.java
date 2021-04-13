package br.com.tictactoe.core;

import br.com.tictactoe.constants.Constants;
import br.com.tictactoe.ui.UI;

public class Board {

	private char[][] matrix;
	
	public Board() {
		matrix = new char[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
		clear();
	}
	
	public void clear() {
		for(int linha = 0; linha < matrix.length; linha++) {
			for(int coluna = 0; coluna < matrix[linha].length; coluna++) {
				matrix[linha][coluna] = ' ';
			}
		}
	}
	
	public void print() {
		UI.printNewLine();
		for(int linha = 0; linha < matrix.length; linha++) {
			for(int coluna = 0; coluna < matrix[linha].length; coluna++) {
				UI.printTextWithoutNewLine(String.valueOf(matrix[linha][coluna]));
				if(coluna != (matrix[linha].length - 1)) {
					UI.printTextWithoutNewLine(" | ");
				}
			}
				UI.printNewLine();
				if(linha < (matrix.length - 1)) {
				UI.printText("---------");
			}
		}
	}
	
	public boolean isFull() {
		for(int linha = 0; linha < matrix.length; linha++) {
			for(int coluna = 0; coluna < matrix[linha].length; coluna++) {
				if (matrix[linha][coluna] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean play(Player player, Move move) throws InvalidMoveException{

		if(move.getLinha() < 0 || move.getColuna() < 0 || move.getLinha() >= Constants.BOARD_SIZE || move.getColuna() >= Constants.BOARD_SIZE) {
			throw new InvalidMoveException("O intervalo da jogada é inválido");
		}
		
		if(matrix[move.getLinha()][move.getColuna()] != ' ') {
			throw new InvalidMoveException("Esta jogada já foi realizada");
		}
		
		matrix[move.getLinha()][move.getColuna()] = player.getSymbol();
			
		return checkRows(player) || checkColumns(player) || checkDiagonal1(player) || checkDiagonal2(player);
	}
	
	
	
	private boolean checkRows(Player player) {
		for(int linha = 0; linha < matrix.length; linha ++) {
			if(checkRow(linha, player)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkRow(int linha, Player player) {
		for(int coluna = 0; coluna < matrix[linha].length; coluna++) {
			if(matrix[linha][coluna] != player.getSymbol()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkColumn(int coluna, Player player) {
		for(int linha = 0; linha < matrix.length; linha++) {
			if(matrix[linha][coluna] != player.getSymbol()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkColumns(Player player) {
		for(int coluna = 0; coluna < Constants.BOARD_SIZE; coluna ++) {
			if(checkColumn(coluna, player)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkDiagonal1(Player player) {
	
		for(int i = 0; i < Constants.BOARD_SIZE; i++) {
			if(matrix[i][i] != player.getSymbol()) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkDiagonal2(Player player) {
		int lastLine = Constants.BOARD_SIZE - 1;
		for(int i = lastLine, j = 0; i >= 0; i--, j++) {
			if(matrix[i][j] != player.getSymbol()) {
				return false;
			}
		}
		return true;
	}
}
