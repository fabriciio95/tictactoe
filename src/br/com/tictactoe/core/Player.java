package br.com.tictactoe.core;

import br.com.tictactoe.ui.UI;

public class Player {
	
	private String nome;
	private Board board;
	private char symbol;
	
	
	public Player(String nome, Board board, char symbol) {
		this.nome = nome;
		this.board = board;
		this.symbol = symbol;
	}

	private Move inputMove() throws InvalidMoveException{
		String moveStr = UI.readInput("Jogador " + this.nome + " ' =>");
		return new Move(moveStr);
	}
	
	public boolean play() throws InvalidMoveException{
		Move move = inputMove();
		return board.play(this, move);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}
