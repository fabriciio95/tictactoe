package br.com.tictactoe.ui;

import java.util.Scanner;

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
	
	@SuppressWarnings("resource")
	public static String readInput(String text) {
		printTextWithoutNewLine(text + " ");
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
	
}
