import java.io.*;
import java.net.*;

public class Client {
    // initialize socket and input output streams
	private BattleshipGUI gui;
    private Socket socket = null;
    private static DataInputStream input = null;
    private static DataOutputStream out = null;
    
    private static ObjectInputStream ois = null;
    private static ObjectOutputStream oos = null;
 
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
        while (true) {
        	try {
                //line = input.readLine();
                //out.writeUTF(line);
                //out.writeUTF("PLAY");
                out.flush();
                //oos.writeObject(missle);
                
                //oos.flush();
            }
            catch (IOException i) {
                System.out.println(i);
            }
        }
        
        
       
    }
 
    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5090);
       
      
    }
    
    public static void sendClickInput(String pos) {
    	try {
			out.writeUTF(pos);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}