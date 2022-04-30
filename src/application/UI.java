package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));

			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro na leitura da posição. Valores válidos são de a1 até h8");
		}

	}
	
	public static  void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println("Turno : " + chessMatch.getTurn());
		
		if(!chessMatch.getCheckMate()) {
			System.out.println("Aguardando jogada : " + chessMatch.getCurrentPlayer());
			if(chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
			
		}else {
			System.out.println("Cheque Mate!");
			System.out.println("Vencedor: " + chessMatch.getCurrentPlayer());
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_RED + (8 - i) + " "  + ANSI_RESET);
			for (int x = 0; x < pieces.length; x++) {
				printPiece(pieces[i][x], false);
			}
			System.out.println();
		}

		System.out.println(ANSI_RED + "  a b c d e f g h" + ANSI_RESET);

	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_RED + (8 - i) + " "  + ANSI_RESET);
			for (int x = 0; x < pieces.length; x++) {
				printPiece(pieces[i][x], possibleMoves[i][x]);
			}
			System.out.println();
		}

		System.out.println(ANSI_RED + "  a b c d e f g h" + ANSI_RESET);

	}

	private static void printPiece(ChessPiece piece, boolean background) {
		String color; 
		if(background) {
			color = ANSI_GREEN;
			
		}else {
			color = ANSI_WHITE;
		}	
		
		if (piece == null) {
			System.out.print(color	+ "□" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_BLUE + piece + ANSI_RESET);
			}

		}
		System.out.print(" ");
	}
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.parallelStream()
				.filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		
		List<ChessPiece> black = captured.parallelStream()
				.filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Peças capturadas");	
		System.out.print("White : [ ");
		System.out.print(ANSI_WHITE);
		for (ChessPiece chessPiece : white) {
			System.out.print(chessPiece.toString() + " ");	
		}
		System.out.print(" ]");
		System.out.print(ANSI_RESET);
		System.out.println("");
		
		System.out.print(ANSI_BLUE);
		System.out.print("Black : [ ");
		for (ChessPiece chessPiece : black) {
			System.out.print(chessPiece.toString() + " ");	
		}
		System.out.print(" ]");
		System.out.print(ANSI_RESET);
		System.out.println("");
		
	}

}
