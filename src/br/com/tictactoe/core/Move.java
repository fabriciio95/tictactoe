package br.com.tictactoe.core;

public class Move {

	private int linha;
	private int coluna;
	
	public Move(String moveStr) throws InvalidMoveException {
		try {
			String[] tokens = moveStr.split(",");
			this.linha = Integer.parseInt(tokens[0]);
			this.coluna = Integer.parseInt(tokens[1]);
		} catch(Exception e) {
			throw new InvalidMoveException("A jogada é inválida");
		}
	}
	
	public int getLinha() {
		return linha;
	}
	
	public int getColuna() {
		return coluna;
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}
	
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
}
