package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}	
	
	@Override
	public String toString() {
		return "K";	
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][]  mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 8);
		
		//acima
		p.setValues(position.getRow() - 1, position.getComlumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
		//baixo
		p.setValues(position.getRow() + 1, position.getComlumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
		//esqueda
		p.setValues(position.getRow(), position.getComlumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
		//direita
		p.setValues(position.getRow(), position.getComlumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
		//noroeste
		p.setValues(position.getRow() - 1, position.getComlumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
		//nordeste
		p.setValues(position.getRow() - 1, position.getComlumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
		//sudoeste
		p.setValues(position.getRow() + 1, position.getComlumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
		//sudeste
		p.setValues(position.getRow() + 1, position.getComlumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
		
		
		return mat;
	}

}
