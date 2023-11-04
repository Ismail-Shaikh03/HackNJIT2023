
public class Tile {
	
	private boolean isOccupied;
	private String shipName;
	
	public Tile() {
		shipName = "";
		isOccupied = false;
	}
	
	public Tile(boolean isOccupied, String shipName) {
		this.shipName = shipName;
		this.isOccupied = isOccupied;
	}
	
	public boolean checkIsOccupied() {
		return isOccupied;
	}
	
	public String getOccupyingShipName(){
		return shipName;
	}
	
	public void setName(String name) {
		this.shipName = name;
	}
	
	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

}
