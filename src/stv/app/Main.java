package stv.app;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application
{
	private static CommandProcessor cmdProc;
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		Button btn = new Button();
		btn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0)
			{
				System.out.println("Hi");
			}
			
		});
		btn.setText("Test");
		
		
		// TODO set-up GUI
		
		StackPane stack = new StackPane();
		stack.getChildren().add(btn);
		
		stage.setScene(new Scene(stack, 300, 250));
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
}
