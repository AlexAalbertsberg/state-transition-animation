package stv.app;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
	private static CommandProcessor cmdProc;
	private static Stage stage;
	private static SocketListener s;
	
	@Override
	public void start(Stage stage) throws Exception 
	{		
		Main.stage = stage;
	}
	
	public static void init(double width, double height)
	{
		Pane p = new Pane();
		
		stage.setScene(new Scene(p, width, height));
		cmdProc.setStage(stage);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		cmdProc = new CommandProcessor();
		
		s = new SocketListener(cmdProc);
		s.listen();
		
		// launch GUI
		launch();
	}
}
