import java.net.*;
import java.io.*;
 
public class Server
{

	private enum turnState{
		PLAYER1,
		PLAYER2,
		PREPPHASE,
		GAMEOVER
	}
	
	private turnState GameState;
	
    //Server Inputs/Outputs
    private Socket            socket   = null;
    private ServerSocket      server   = null;
    private DataInputStream   in       = null;
    private DataOutputStream   out     = null;
    private ObjectInputStream oins     = null;
    private ObjectOutputStream oos     = null;
    
    //Server Variables
    private Tile[][] mapTiles = new Tile[9][9];
 
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
            System.out.println("Client accepted");
 
            // takes input from the client socket
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            oins = new ObjectInputStream(socket.getInputStream());
 
            String line = "";
 
            // Get Command and send it to commandSwitcher
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    //checkIsTiledOccupied(line);
                    //out.writeBoolean(checkIsTiledOccupied(line));
                    System.out.println(line);
                    
                }
                catch(IOException i)
                {
                    System.out.println(i);
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
        Server server = new Server(5090);
    }
    
    
    
    public void populateTiles(String[] tilePositions,String shipName) {
    	
    	for (String pos : tilePositions) {
    		//[0] = Row | [1] = Column
    		String[] cordinates;
    		cordinates = pos.split("\\|");
    		
    		int row = Integer.parseInt(cordinates[0]);
    		int column = Integer.parseInt(cordinates[1]);
    		mapTiles[row][column].setIsOccupied(true);
    		mapTiles[row][column].setName(shipName);
    	}
    	
    }
    
    public boolean checkIsTiledOccupied(String position) {
    	String[] cordinates;
		cordinates = position.split("\\|");
		
		int row = Integer.parseInt(cordinates[0]);
		int column = Integer.parseInt(cordinates[1]);
		
		return mapTiles[row][column].checkIsOccupied();
    }
    
    private void commandSwitcher(String command, SendableItem t) {
    	switch (command) {
    		case "PLACE":
    			
    			//populateTiles();
    			break;
    		case "ATTACK":
    			break;
    		case "EVENT":
    			break;
    	}
    }
    
    private void startGame() {
    	
    }
}