
public class Cell {
	private int x;
	private int y;
	
	private PlayerType value;
	
	public Cell(int coordinate) {
		// assert coord 1-9!
		if(coordinate>=1 && coordinate <=9)
			this.value = null;
		setXY(coordinate);
	}
	
	public void setValue(PlayerType value) {
		this.value = value;
	}
	
	public PlayerType getValue() {
		return this.value;
	}
	
	public boolean isEmpty() {
		return this.value == null;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getCoord() {
		return (y-1)*3 + x; // (row - 1) * 3 + column
	}
	
	private void setXY(int coord) {	
		this.x = coord % 3 == 0 ? 3 : coord % 3; // columns
		this.y = (int)Math.ceil((double)coord / 3.0f); // rows
	}
	
	public String toString() {
		return this.value == null ? "-" : this.value.value;
	}
}
