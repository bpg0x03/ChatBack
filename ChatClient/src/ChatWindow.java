import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatWindow extends VBox{
	private HBox topHBox = new HBox();
	private HBox botHBox = new HBox();
	private TextArea taChatBox = new TextArea();
	private TextField tfInput = new TextField();
	private Button btDisconnect = new Button("Disconnect ");
	private Stage mStage = new Stage();
	private String name = "";
	private static final int chatBoxWidth = 500;
	private static final int sceneWidth = 500;
	private static final int sceneHeight = 200;
	
	public ChatClientHandler client;
	private String ip = "127.0.0.1";
	private int port = 8081;

	public ChatWindow() { 
		tfInput.setPrefWidth(chatBoxWidth-100);
		taChatBox.setPrefWidth(chatBoxWidth);
		taChatBox.setEditable(false);
		taChatBox.setPadding(new Insets(5,5,5,5));

		topHBox.getChildren().addAll(taChatBox);
		botHBox.getChildren().addAll(tfInput, btDisconnect);

		this.getChildren().addAll(topHBox, botHBox);
		
		client = new ChatClientHandler(ip, port, taChatBox);
	}
	
	public void displayWindow() {
		Scene scene = new Scene(this, sceneWidth, sceneHeight);
		mStage.setTitle("Chatting as " + name);
		mStage.setScene(scene);
		mStage.show();
		client.connect();
		client.open();
		client.start();
	}

	public Button getBtDisconnect() {
		return btDisconnect;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	
	public String getInputText() {
		return name + ": " + tfInput.getText();
	}
	
	public void write(String text){
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
		mStage.close();
	}
}