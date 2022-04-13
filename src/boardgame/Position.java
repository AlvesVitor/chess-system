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
	
	public void setValues(int row, int column) {
		this.row = row;
		this.comlumn = column;
	}
	
	@Override
	public String toString() {
		return row + " , " + comlumn;	
	}
	
	

}
