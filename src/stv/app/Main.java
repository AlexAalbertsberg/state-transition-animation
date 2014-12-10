package stv.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		// TODO set-up GUI
		
		stage.show();
	}
	
	public static void main(String[] args){
		CommandProcessor cmdProc = new CommandProcessor();
		
		SocketListener s = new SocketListener(cmdProc);
		s.listen();
		
		
		// launch GUI
		launch();
	}

}
