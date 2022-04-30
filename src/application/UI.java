package application;

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
	
	public static final String[] numbers = {"➊", "➋", "➌", "➍", "➎", "➏", "➐", "➑"};
	public static final String letters = "  🅰 🅱 🅲 🅳 🅴 🅵 🅶 🅷";

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

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println("Turno : " + chessMatch.getTurn());

		if (!chessMatch.getCheckMate()) {
			if (chessMatch.getCurrentPlayer() == Color.BLACK) {
				System.out.println("Aguardando jogada : Pretas");
			} else {
				System.out.println("Aguardando jogada : Brancas	");
			}

			if (chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}

		} else {
			System.out.println("Cheque Mate!");
			if (chessMatch.getCurrentPlayer() == Color.BLACK) {
				System.out.println("Vencedor: Pretas 🎖" );
			} else {
				System.out.println("Vencedor: Brancas 🎖");
			}
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_RED  + numbers[7 - i] + " " + ANSI_RESET);
			if (i % 2 == 0) {
				for (int x = 0; x < pieces.length; x++) {
					printPiece(pieces[i][x], false, x);
				}

			} else {
				for (int x = 0; x < pieces.length; x++) {
					printPiece(pieces[i][x], false, x + 1);
				}
			}
			System.out.println();
		}

		System.out.println(ANSI_RED + letters + ANSI_RESET);

	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves, Color color) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_RED + numbers[7 - i] + " " + ANSI_RESET);
			if (i % 2 == 0) {
				for (int x = 0; x < pieces.length; x++) {
					printPiece(pieces[i][x], possibleMoves[i][x], x, color);
				}
			} else {

				for (int x = 0; x < pieces.length; x++) {
					printPiece(pieces[i][x], possibleMoves[i][x], x + 1	, color);
				}
			}
			System.out.println();
		}

		System.out.println(ANSI_RED + letters + ANSI_RESET);

	}

	private static void printPiece(ChessPiece piece, boolean background, int x, Color cor) {

		if (piece == null) {
			if (background) {
				System.out.print(ANSI_GREEN + "■" + ANSI_RESET);
			} else {
				if (x % 2 == 0) {
					System.out.print(ANSI_WHITE + "■" + ANSI_RESET);
				} else {
					System.out.print(ANSI_BLACK + "■" + ANSI_RESET);
				}
			}
		} else {
			if (cor != piece.getColor() && background == true) {
				System.out.print(ANSI_GREEN + piece + ANSI_RESET);
			} else {

				if (piece.getColor() == Color.WHITE) {
					System.out.print(ANSI_WHITE + piece + ANSI_RESET);
				} else {
					System.out.print(ANSI_BLUE + piece + ANSI_RESET);
				}
			}

		}
		System.out.print(" ");
	}

	private static void printPiece(ChessPiece piece, boolean background, int x) {

		if (piece == null) {
			if (background) {
				System.out.print(ANSI_GREEN + "■" + ANSI_RESET);
			} else {
				if (x % 2 == 0) {
					System.out.print(ANSI_WHITE + "■" + ANSI_RESET);
				} else {
					System.out.print(ANSI_BLACK + "■" + ANSI_RESET);
				}
			}
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
		List<ChessPiece> white = captured.parallelStream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());

		List<ChessPiece> black = captured.parallelStream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList());

		System.out.println("Peças capturadas");
		System.out.print("Brancas : [ ");
		System.out.print(ANSI_WHITE);
		for (ChessPiece chessPiece : white) {
			System.out.print(chessPiece.toString() + " ");
		}
		System.out.print(" ]");
		System.out.print(ANSI_RESET);
		System.out.println("");

		System.out.print(ANSI_BLUE);
		System.out.print("Pretas : [ ");
		for (ChessPiece chessPiece : black) {
			System.out.print(chessPiece.toString() + " ");
		}
		System.out.print(" ]");
		System.out.print(ANSI_RESET);
		System.out.println("");

	}

}
