

public class Submarine extends IShip{

    private int length = 2;




    public Submarine(String pivotPosition) throws Exception {
        positions.add(pivotPosition);
        String[] tempArray = pivotPosition.split("\\|");
        hPosPivot = Integer.parseInt(tempArray[0]);
        vPosPivot = Integer.parseInt(tempArray[1]);
        createPos();

    }


}
