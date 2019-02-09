import java.awt.event.ActionListener;
import java.awt.event.*;
public class ChatClientDriver{

    private static GreetWindow greetWindow = new GreetWindow();
    private static ChatWindow chatWindow = new ChatWindow();

	
	public  static void main(String[] args) {



		greetWindow.show();
		greetWindow.getBtSubmit().addActionListener(new ActionListener() {//finds when the button is pressed
			
			public void actionPerformed(ActionEvent e) {
				String name = greetWindow.getName().getText();
				String ip = greetWindow.getIp().getText();
				chatWindow.setName(name);
				chatWindow.setIp(ip);

				greetWindow.closeWindow();
				chatWindow.show();
				
				//firstListener
				chatWindow.getTfInput().addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent e){
						String userInput = chatWindow.getInputText();
						chatWindow.write(userInput);

					}});
				
				//second listener
				chatWindow.getBtDisconnect().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						chatWindow.disconnect();
					}

				});
			}
		});
		
	}
}
