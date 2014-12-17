package stv.command;

import javafx.application.Platform;
import javafx.css.Styleable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import stv.app.Main;
import stv.json.JSONObject;

public class SetCommand extends AbsCommand
{
	// The node's id.
	private String id;
	// The node's X position.
	private int positionX;
	// The node's Y position.
	private int positionY;
	// The node to be set on the GUI.
	private Node node;
	
	private String colorCode;
	
	public SetCommand(JSONObject json, Stage stage) {
		super(json, stage);
	}

	@Override
	public void performCommand() 
	{
		System.out.println("Performing set command");
		if(json.has("id"))
		{
			id = json.getString("id"); 
		}
		if(json.has("posx"))
		{
			positionX = json.getInt("posx");
		}
		if(json.has("posy"))
		{
			positionY = json.getInt("posy");
		}
		if(json.has("colorCode"))
		{
			colorCode = json.getString("colorCode");
		}
		
		if(json.has("shape"))
		{
			String shapeStr = json.getString("shape");
			
			if(shapeStr.equals("circle"))
			{
				node = new Circle(positionX,positionY,25, Paint.valueOf(colorCode));
			}
		}		
		Main.addControl(id, node);
		
		Platform.runLater(new Runnable()
		{

			@Override
			public void run()
			{
				Pane sp = (Pane)stage.getScene().getRoot();
				sp.getChildren().add(node);
			}
			
		});
		System.out.println("Set command execution complete");
	}	 
}
