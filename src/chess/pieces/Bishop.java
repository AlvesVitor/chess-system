package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		//noroeste
		p.setValues(position.getRow() - 1, position.getComlumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
			p.setValues(p.getRow() - 1, p.getComlumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
        //nordeste
		p.setValues(position.getRow() - 1, position.getComlumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
			p.setValues(p.getRow() - 1, p.getComlumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
        //sudeste
		p.setValues(position.getRow() + 1, position.getComlumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
			p.setValues(p.getRow() + 1, p.getComlumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
        //sudoeste
		p.setValues(position.getRow() + 1, position.getComlumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
			p.setValues(p.getRow() + 1, p.getComlumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}

		return mat;
	}

}
