package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";	
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//acima da peça
		p.setValues(position.getRow() - 1, position.getComlumn());
		while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getComlumn()] = true;
		}
		
		//esquerda da peça
		p.setValues(position.getRow(), position.getComlumn() - 1);
				while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getComlumn()] = true;
					p.setComlumn(p.getComlumn() - 1);
				}
				if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getComlumn()] = true;
				}
				

				//direita da peça
				p.setValues(position.getRow(), position.getComlumn() + 1);
						while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
							mat[p.getRow()][p.getComlumn()] = true;
							p.setComlumn(p.getComlumn() + 1);
						}
						if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
							mat[p.getRow()][p.getComlumn()] = true;
						}
						
						
						//baixo da peça
						p.setValues(position.getRow() + 1, position.getComlumn());
								while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
									mat[p.getRow()][p.getComlumn()] = true;
									p.setRow(p.getRow() + 1);
								}
								if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
									mat[p.getRow()][p.getComlumn()] = true;
									}	
		
		return mat;		
	}

}
