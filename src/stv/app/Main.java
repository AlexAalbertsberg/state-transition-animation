package stv.app;



import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application
{
	private static ArrayList<Node> controls;
	private static CommandProcessor cmdProc;
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		controls = new ArrayList<Node>();
		// TODO setup GUI
		
		Pane p = new Pane();
		
		Button btn = new Button();
		btn.setText("test");
		
		p.getChildren().add(btn);
		Image img = new Image("tree.jpg");
		ImageView imgView = new ImageView(img);
		p.getChildren().add(imgView);
		
		stage.setScene(new Scene(p, 800, 800));
		cmdProc.setStage(stage);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		cmdProc = new CommandProcessor();
		
		SocketListener s = new SocketListener(cmdProc);
		s.listen();
		
		// launch GUI
		launch();
	}
	
	public static void addControl(String id, Node n)
	{
		n.setId(id);
		controls.add(n);
	}
	
	public static void removeControl(String id)
	{
		for(int i = 0; i < controls.size(); i++)
		{
			if(controls.get(i).getId().equals(id))
			{
				controls.remove(i);
			}
		}
	}
	
	public static Node getControl(String id)
	{
		for(int i = 0; i < controls.size(); i++)
		{
			if(controls.get(i).getId().equals(id))
			{
				return controls.get(i);
			}
		}
		return null;
	}
}
