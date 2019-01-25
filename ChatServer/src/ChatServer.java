import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer implements Runnable {
	
    public String chat = "";
    private ServerSocket server;
    private Thread thread;
    private List<ChatClientHandler> clients;
    
    public static void main(String args[]) {
        ChatServer server = new ChatServer();
        server.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
    }

    public ChatServer(){
        try{
            System.out.println("Binding ChatBackServer to port 8081!");
            server = new ServerSocket(8081);
            clients = new ArrayList<ChatClientHandler>();
        }catch(IOException e){System.out.println(e);}
    }

    public void run(){
        while(thread != null){
            try{
                System.out.println("Awaiting client connection!");
                newSession(server.accept());
            }catch(IOException ie){System.out.println("ERROR establishing connection! " + ie);}
        }
    }
    
	public void sendAll(String text) throws IOException {
		for (ChatClientHandler client : clients) {
			client.send(text);
		}
	}

    public void newSession(Socket socket){
    	System.out.println("Client connected!");
    	ChatClientHandler client = new ChatClientHandler(socket, this);
    	clients.add(client);
        try{
            client.open();
            client.start();
        }catch(IOException ioe){System.out.println("Error starting session! " + ioe );}
    }

    public void start(){
        if (thread == null){
            thread = new Thread (this);
            thread.start();
        }
    }
    
    public void removeClient(ChatClientHandler client) {
    	clients.remove(client);
    }

    public void stop() throws InterruptedException{
        if (thread != null){
            thread.join();
            thread =null;
        }
    }
}