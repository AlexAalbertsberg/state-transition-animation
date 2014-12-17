package stv.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXTest extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("JavaFXTest");
		
		Button btn = new Button();
		btn.setText("Button 1");
		btn.setMinSize(200, 200);
		btn.setFont(new Font(30));
		btn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Hi!");		
			}
			
		});
		
		StackPane pane = new StackPane();
		pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		pane.getChildren().add(btn);
		stage.setScene(new Scene(pane,1200,1200));
		
		stage.sizeToScene();
		stage.show();
	}
	
	public static void main(String[] args){
		launch();
	}
}
