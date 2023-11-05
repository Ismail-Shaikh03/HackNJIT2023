import java.net.*;
import java.util.ArrayList;
import java.io.*;
 
public class Server
{

	private enum turnState{
		PLAYER1,
		PLAYER2,
		PREPPHASE,
		GAMEOVER
	}
	
	private static turnState GameState;
	
    //Server Inputs/Outputs
    private Socket            socket   = null;
    private ServerSocket      server   = null;
    
    private DataInputStream   in       = null;
    //private DataOutputStream   out     = null;
    
    private ObjectInputStream  oins    = null;
    //private ObjectOutputStream oos     = null;
    
    
    //Server Variables
    private static Tile[][] mapTiles = new Tile[10][10];
    private static ArrayList<IShip> playerShips = new ArrayList<>();
    
    private boolean clientsConnected = false;
    
    public static boolean clickInitiated = false;
    public static String returnBool = "";
 
    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");
 
            System.out.println("Waiting for a client ...");
 
            socket = server.accept();
            clientsConnected = true;
            System.out.println("Client accepted");
            
            startGame();
 
            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            oins = new ObjectInputStream(socket.getInputStream());
            
            //oos = new ObjectOutputStream(socket.getOutputStream());            
            //out = new DataOutputStream(socket.getOutputStream());
 
            String commandLine = "";
            String inputLine = "";
            IShip inputShip = null;
 
            // Get Command and send it to commandSwitcher
            while (!commandLine.equals("COMMANDS"))
            {
                try
                {
                    commandLine = in.readUTF();
                    inputLine = in.readUTF();
                    inputShip = (IShip) oins.readObject();
                    playerShips.add(inputShip);
                    System.out.println(commandLine);
                    commandSwitcher(commandLine, inputShip.getPos());
                    //System.out.print(checkIsTiledOccupied(line));
                    //out.writeBoolean(checkIsTiledOccupied(line));
                    
                }
                catch(IOException i)
                {
                    System.out.println(i);
                } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

 
            // close connection
            System.out.println("Closing connection");
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println("ioexception" + i);
        }
    }
 
    public static void main(String args[])
    {
        Server server = new Server(5100);
    }
    

    public void populateTiles(ArrayList<String> tilePositions) {
    	
    	for (String pos : tilePositions) {
    		//[0] = Row | [1] = Column
    		String[] cordinates;
    		cordinates = pos.split("\\|");
    		
    		int row = Integer.parseInt(cordinates[0]);
    		int column = Integer.parseInt(cordinates[1]);
    		mapTiles[row][column].setIsOccupied(true);
    		//mapTiles[row][column].setName(shipName);
    	}
    	
    }
    
    public boolean checkIsTileOccupied(String position) {
    	String[] cordinates;
		cordinates = position.split("\\|");
		
		int row = Integer.parseInt(cordinates[0]);
		int column = Integer.parseInt(cordinates[1]);
		
		return mapTiles[row][column].checkIsOccupied();
		
    }
    
    public boolean checkAreTilesOccupied(ArrayList<String> positions) {
    	boolean occupied = false;
    	for (String pos : positions) {
    		String[] cordinates;
    		cordinates = pos.split("\\|");
    		
    		int row = Integer.parseInt(cordinates[0]);
    		int column = Integer.parseInt(cordinates[1]);
    		
    		occupied = mapTiles[row][column].checkIsOccupied();
    	}
    	return occupied;
		
    }
    
    private int[] decodeCords(String cord) {
    	String[] cordinates;
		cordinates = cord.split("\\|");
		
		int row = Integer.parseInt(cordinates[0]);
		int column = Integer.parseInt(cordinates[1]);
		
		return new int[]{row, column};
    }
    
    private void commandSwitcher(String command, ArrayList<String> input) {
    	
    	switch (command) {
    		case "PLACE":
    			System.out.println("hey" + checkAreTilesOccupied(input));
    			if (!checkAreTilesOccupied(input)) {
    				populateTiles(input);
    				//System.out.println(checkIsTileOccupied(input.get(0)) + " Test Case: " + input.get(0));
    				
    			}
    			else {
    				
    			}
    			break;
    		case "ATTACK":
    			clickInitiated = true;
    			//Checks if Ship is at Tile
    			//checkIsTileOccupied(input.get(0)
    			if (true) {
    				System.out.println("In Attack");
    				returnBool = "returned";
    				//Client.getResults();
					//out.writeUTF("hey");
    				
					System.out.println("Out Attack");
					//out.flush();
    			}
    			break;
    		case "EVENT":
    			break;
    	}
    }
    
    public static String getReturnBool() {
    	return returnBool;
    }
    
    private static void initalizeGrid() {
    	for (int i = 0;i < 10;i++) {
    		for (int j = 0;j < 10;j++) {
    			mapTiles[i][j] = new Tile();
    		}
    	}
    }
    
    private static void startGame() {
    	GameState = turnState.PREPPHASE;
    	initalizeGrid();
    }
    
}