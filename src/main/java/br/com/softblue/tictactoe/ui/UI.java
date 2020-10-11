package br.com.softblue.tictactoe.ui;

import br.com.softblue.commons.io.Console;

public class UI {
	
	public static void printText(String text){
		System.out.println(text);
	}
	
	public static void printTextWithoutNewLine(String text) {
		System.out.print(text);
	}
	
	public static void printNewLine() {
		System.out.println();
	}
	
	public static void printGameTitle() {
		printText("=================");
		printText("| JOGO DA VELHA |");
		printText("=================");
		printNewLine();
	}
	
	public static String readInput(String text) {
		printTextWithoutNewLine(text + " ");
		return Console.readString();
	}
	
	
}
