import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatClientHandler implements Runnable{

	private Thread thread;
	private Socket socket;
	private int port;
	private String ip;
	private JTextArea field;
	private DataInputStream fromClient = null;
	private DataOutputStream toClient = null;

	public ChatClientHandler(String ip, int port, JTextArea textField){
		socket = new Socket();
		this.port = port;
		this.ip = ip;
		field = textField;
	}

	public void run(){
		System.out.println("Client connected using port: "+ port);
		while(thread != null){
			try {
				final String text = fromClient.readUTF() + "\n";	//links local variables (textArea) to ChatWindow
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						field.append(text);
					}
				});
				// System.out.println(server.chat);
				// toClient.writeUTF(server.chat);
				// toClient.flush();
			} catch(IOException ie){
				System.out.println("Disconnected from server.");
				System.out.println(ie);
				close();
				stop();
			}
		}
	}

	public void connect() {
		InetSocketAddress address = new InetSocketAddress(ip, port);
		try {
			socket.connect(address);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String text) throws IOException {
		toClient.writeUTF(text);
		toClient.flush();
	}

	public void open() {
		try {
			fromClient = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			toClient = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		if (fromClient != null){ 
			try {
				fromClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		if (socket != null){ 
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	public void start(){
		if (thread == null){
			thread = new Thread (this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null){
			try {
				thread.join(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			thread = null;
		}
	}
}
