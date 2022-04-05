package boardgame;

public class Position {
	
	private int row;
	private int comlumn;
	
	
	
	public int getRow() {
		return row;
	}



	public void setRow(int row) {
		this.row = row;
	}



	public int getComlumn() {
		return comlumn;
	}



	public void setComlumn(int comlumn) {
		this.comlumn = comlumn;
	}



	public Position(int row, int comlumn) {
		this.row = row;
		this.comlumn = comlumn;
	}
	
	@Override
	public String toString() {
		return row + " , " + comlumn;	
	}
	
	

}
