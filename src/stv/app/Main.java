package stv.app;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Main class derives from JavaFX's application class. 
 * This allows it to create a stage upon which the GUI can be built.
 * @author Alex Aalbertsberg
 */
public class Main extends Application
{
	private static CommandProcessor cmdProc;
	private static Stage stage;
	private static SocketListener s;
	
	@Override
	public void start(Stage stage) throws Exception 
	{		
		// Passes stage to a static reference so that it may be used in the init method.
		Main.stage = stage;
	}
	
	/**
	 * The init method is called by the init command when it is issued by a client.
	 * Creates the GUI with the passed parameters
	 * @param width - GUI width
	 * @param height - GUI height
	 */
	public static void init(double width, double height)
	{		
		Pane p = new Pane();
		
		// Create new scene with specified width and height
		stage.setScene(new Scene(p, width, height));
		// pass current stage to the command processor
		cmdProc.setStage(stage);
		// display the stage on the screen
		stage.show();
		stage.centerOnScreen();
	}
	
	/**
	 * Main method. This method is called first upon execution.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Create new command processor
		cmdProc = new CommandProcessor();
		
		// Create new socket listener and pass the command processor to it.
		s = new SocketListener(cmdProc);
		// Start listening
		s.listen();
		
		// launch GUI
		launch();
	}
}
