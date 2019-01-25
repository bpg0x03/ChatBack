import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GreetWindow extends HBox{
	private TextField input = new TextField();
	private TextField tfIp = new TextField();
	private Button btSubmit = new Button("Submit");
	private String ip = "";
	private Stage stage = new Stage();
	private String name = "";
	public static final int width = 225;
	public static final int height = 50;
	
	public GreetWindow() { //uselses consturctor
		VBox vBox = new VBox();
		vBox.getChildren().addAll(tfIp, input);
		this.getChildren().addAll(vBox, btSubmit);
		input.setText("Enter your name here");
		tfIp.setText("Enter server Ip here");
	}
	
	
	public Button getBtSubmit() {
		return btSubmit;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	
	public String getIp(){
		return ip;
	}


	public String getName() {
		return name;
	}
	
	public void closeWindow() {
		setName(input.getText());
		setIp(tfIp.getText());
		stage.close();
	}
	
	public void displayWindow() {
		
		Scene scene = new Scene(this, width, height );
		stage.setTitle("Please enter your name");
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	

}
