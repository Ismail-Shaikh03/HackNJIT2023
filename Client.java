import java.io.*;
import java.net.*;

public class Client {
    // initialize socket and input output streams
	private BattleshipGUI gui;
    private Socket socket = null;

    private static DataOutputStream out = null;
    private static ObjectOutputStream oos = null;
    
    private static String temp;
 
    // constructor to put ip address and port
    public Client(String address, int port)
    {
    	gui = new BattleshipGUI();
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
 
            // sends output to the socket
            oos = new ObjectOutputStream(socket.getOutputStream());            
            out = new DataOutputStream(socket.getOutputStream());
            
            

        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }
        
        Missle missle = new Missle("9|9");
       
        
        
        // keep reading until "Over" is input

    }
 
    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5100);  
        
    }
    
    public static void sendClickInput(String pos) {

    	try {
    		//Submarine sub = new Submarine(pos);
    		IShip sub = new Carrier(pos);
    		System.out.print(sub.getPos().get(0) + sub.getPos().get(4));
    		out.writeUTF("ATTACK");
			out.writeUTF(pos);
			oos.writeObject(sub);
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	whileLoop();

    }
    
    private static void whileLoop() {
    	while (temp == null) {
        	System.out.print("while");
        	getResults();
        }
    	System.out.print("Hey | " +temp.length());
    	
    }
    
    public static void getResults() {
    	System.out.print("called");
    	temp = Server.getReturnBool();
    	System.out.print(temp);
    }
}