import java.util.ArrayList;

public class Submarine implements IShip{

    private int length = 2;
    private Boolean alive = true;

    private int vPosPivot; //columns
    private int hPosPivot; //rows(row/ column)


    private ArrayList<String> positions = new ArrayList<>();





    public Submarine(String pivotPosition) throws Exception {
        positions.add(pivotPosition);
        String[] tempArray = pivotPosition.split("\\|");
        hPosPivot = Integer.parseInt(tempArray[0]);
        vPosPivot = Integer.parseInt(tempArray[1]);
        createPos();

    }

    public void checkDamage(){

    }
    public ArrayList<String> getPos(){
        return positions;
    }



    public void createPos() throws Exception {
        //Assumes starting vertical position
        int tempVert;
        String tempString;

        for(int i = 0;i < length ;i++){
            tempVert = vPosPivot + 1+i;
            if(tempVert > 9){

                Exception OutOfBounds = new Exception("Out of bounds, tempvert : " + tempVert);
                throw OutOfBounds;
            }
            tempString = hPosPivot +"|" + tempVert;
            positions.add(tempString);

        }

    }

    public void rotate(int degrees) throws Exception {
        int tempVert;
        int tempHor;
        String tempString;
        positions.clear(); //Clears array positions
        positions.add((hPosPivot +"|" + vPosPivot)); //Adds first position back

        switch(degrees){
            case 90:
                for(int i = 0;i < length ;i++){
                    tempVert = vPosPivot -i- 1 ;
                    if(tempVert < 0){

                        Exception OutOfBounds = new Exception("Out of bounds, tempvert : " + tempVert);
                        throw OutOfBounds;
                    }
                    tempString = (hPosPivot +"|" + tempVert); //New position upwards
                    positions.add(tempString);
                }

                break;
            case 180:

                for(int i = 0;i < length ;i++){
                    tempHor = hPosPivot -i- 1 ;
                    if(tempHor < 0){

                        Exception OutOfBounds = new Exception("Out of bounds, tempvert : " + tempHor);
                        throw OutOfBounds;
                    }
                    tempString = (tempHor +"|" + vPosPivot); //New position upwards
                    positions.add(tempString);
                }

                break;
            case 270:
                createPos();
                break;
            case 0:
                for(int i = 0;i < length ;i++){
                    tempHor = hPosPivot +i+ 1 ;
                    if(tempHor > 9){

                        Exception OutOfBounds = new Exception("Out of bounds, tempvert : " + tempHor);
                        throw OutOfBounds;
                    }
                    tempString = (tempHor +"|" + vPosPivot); //New position upwards
                    positions.add(tempString);
                }
                break;

        }


    }
    public String toString(){
        return "Positions: " + getPos();
    }

}
