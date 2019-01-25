import java.net.*;
import java.io.*;

public class ChatClientHandler implements Runnable{
	
	private Thread thread;
	private ChatServer server;
	private Socket socket = null;
	private int id = 0;
	private DataInputStream fromClient = null;
	private DataOutputStream toClient = null;
	
	public ChatClientHandler(Socket newSocket, ChatServer serverReference){
		socket = newSocket;
		server = serverReference;
		id = socket.getPort();
	}
	
	public void run(){
		System.out.println("Client connected using port: "+ id);
		while(thread != null){
			try {
				String text = fromClient.readUTF();
				System.out.println(text);
				server.sendAll(text);
			} catch(IOException ie){
				System.out.println("Client disconnected.");
				try {
					close();
					stop();
					server.removeClient(this);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void send(String text) throws IOException {
		toClient.writeUTF(text);
		toClient.flush();
	}

	public void open() throws IOException{
		 fromClient = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		 toClient = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	
	public void close() throws IOException{
		if (fromClient != null){fromClient.close();}
		if (socket != null){socket.close();}
	}
	
    public void start(){
        if (thread == null){
            thread = new Thread (this);
            thread.start();
        }
    }

    public void stop() throws InterruptedException{
        if (thread != null){
            thread.join();
            thread = null;
        }
    }
}
