import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ChatClientDriver extends Application{
	private GreetWindow greetWindow = new GreetWindow();
	private ChatWindow chatWindow = new ChatWindow();
    
	public static void main(String[] args) {
		Application.launch(args);
	}

	
	public void start(Stage primaryStage) {
		greetWindow.displayWindow();
		greetWindow.getBtSubmit().setOnAction(new EventHandler<ActionEvent>() {//finds when the button is pressed
			
			public void handle(ActionEvent e) {
				
				greetWindow.closeWindow();
				//launches the second window
				chatWindow.setName(greetWindow.getName());
				chatWindow.setIp(greetWindow.getName());
				chatWindow.displayWindow();
				
				//firstListener
				chatWindow.setOnKeyPressed(new EventHandler<KeyEvent> () { //checks for when enter is pressed
					public void handle(KeyEvent f) {
						switch (f.getCode()) {
						case ENTER:
							String userInput = chatWindow.getInputText();
							chatWindow.write(userInput);
						default:
							break;
						}
					}

				});
				
				//second listener
				chatWindow.getBtDisconnect().setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent h) {
						chatWindow.disconnect();
					}
				});
			}
		});
		
	}
}
