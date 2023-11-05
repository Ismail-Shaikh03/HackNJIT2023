import java.io.Serializable;
import java.util.ArrayList;

public abstract class IShip implements Serializable {
    protected int length;
    protected boolean alive = true;

    protected int vPosPivot; //columns
    protected int hPosPivot; //rows(row/ column)


    protected ArrayList<String> positions = new ArrayList<>();

    public IShip(){}

    public void checkDamage(){}
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

    public ArrayList<String> getPos(){
        return positions;
    }

    public String toString(){
        return "Positions: " + positions;
    }




}
