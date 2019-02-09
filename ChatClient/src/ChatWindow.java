import java.io.IOException;
import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class ChatWindow {
	private JFrame stage = new JFrame();
	private JTextArea taChatBox = new JTextArea();
	private JTextField tfInput = new JTextField();
	private JButton btDisconnect = new JButton("Disconnect ");

	private String name = "";
	private static final int chatBoxWidth = 500;
	private static final int sceneWidth = 500;
	private static final int sceneHeight = 200;

	public ChatClientHandler client;
	private String ip = "127.0.0.1";
	private int port = 8081;

	public ChatWindow() {
		stage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		stage.getContentPane().setLayout(new BoxLayout(stage.getContentPane(), BoxLayout.Y_AXIS));
		taChatBox.setEditable(false);

		taChatBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		stage.getContentPane().add(taChatBox);

		tfInput.setAlignmentX(Component.CENTER_ALIGNMENT);
		stage.getContentPane().add(tfInput);

		btDisconnect.setAlignmentX(Component.CENTER_ALIGNMENT);
		stage.getContentPane().add(btDisconnect);

	}


	public JTextField getTfInput() {
		return tfInput;
	}

	public JButton getBtDisconnect() {
		return btDisconnect;
	}

	public void setName(String n) {
		this.name = n;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getInputText() {
		return name + ": " + tfInput.getText();
	}

	public void write(String text) {
		try {
			client.send(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tfInput.setText("");
	}

	public void disconnect() {
		client.close();
		client.stop();
		closeWindow();
	}

	public void closeWindow() {
		stage.dispose();
	}


	public void show(){
		stage.setPreferredSize(new Dimension(chatBoxWidth, chatBoxWidth - 100));
		//tfInput.setPreferredSize(new Dimension(chatBoxWidth-100,50));
		taChatBox.setPreferredSize(new Dimension(chatBoxWidth-25, chatBoxWidth-50));
		stage.setTitle("Chatting as " + name);
		stage.pack();
		stage.setVisible(true);

		client = new ChatClientHandler(ip, port, taChatBox);

		client.connect();
		client.open();
		client.start();
	}
}