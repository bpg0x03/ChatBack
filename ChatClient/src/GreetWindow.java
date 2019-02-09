import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GreetWindow{
	private JTextField input = new JTextField("Enter your name here");
	private JTextField tfIp = new JTextField("Enter server IP here");
	private JButton btSubmit = new JButton("Submit");
	private String ip = "";
	private JFrame stage = new JFrame("ChatBack Chat Client");
	private String name = "";
	public static final int width = 500;
	public static final int height = 500;
	
	public GreetWindow() { //uselses consturctor
		stage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stage.getContentPane().setLayout(new BoxLayout(stage.getContentPane(),BoxLayout.Y_AXIS));

		input.setAlignmentX(Component.CENTER_ALIGNMENT);
		stage.getContentPane().add(input);

		tfIp.setAlignmentX(Component.CENTER_ALIGNMENT);
		stage.getContentPane().add(tfIp);

		btSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
		stage.getContentPane().add(btSubmit);

	}

	
	
	public JButton getBtSubmit() {
		return btSubmit;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	
	public JTextField getIp(){
		return tfIp;
	}


	public JTextField getName() {
		return input;
	}
	
	public void closeWindow() {
		setName(input.getText());
		setIp(tfIp.getText());
		stage.dispose();
	}
	
	public void show(){
		stage.setPreferredSize(new Dimension(300,125));
		stage.pack();
		stage.setVisible(true);
	}
	
	
	

}
