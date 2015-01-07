package stv.command;

import java.io.ByteArrayInputStream;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import stv.json.JSONArray;
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
	public String performCommand() 
	{
		if(json.has(CommandConstants.IDENTIFIER))
		{
			id = json.getString(CommandConstants.IDENTIFIER); 
		}
		if(json.has(CommandConstants.XPOS))
		{
			positionX = json.getInt(CommandConstants.XPOS);
		}
		if(json.has(CommandConstants.YPOS))
		{
			positionY = json.getInt(CommandConstants.YPOS);
		}
		if(json.has(CommandConstants.COLOR))
		{
			colorCode = json.getString(CommandConstants.COLOR);
		}
		if(json.has(CommandConstants.SHAPE))
		{
			String shapeStr = json.getString(CommandConstants.SHAPE);
			
			if(shapeStr.equals(CommandConstants.SHAPE_CIRCLE))
			{
				node = new Circle(positionX,positionY,25, Paint.valueOf(colorCode));
			}
			else if(shapeStr.equals(CommandConstants.SHAPE_RECTANGLE))
			{
				node = new Rectangle(25, 25, Paint.valueOf(colorCode));
				node.setLayoutX(positionX);
				node.setLayoutY(positionY);
			}
		}
		else if(json.has(CommandConstants.IMAGE))
		{
			String byteString = json.getString(CommandConstants.IMAGE);
			byte[] b = null;
			try
			{
				b = Base64.decode(byteString);
			} catch (Base64DecodingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(b != null)
			{
				Image img = new Image(new ByteArrayInputStream(b));
				ImageView view = new ImageView(img);
				view.relocate(positionX, positionY);
			
				node = view;
			}
		}

		// TODO Check for existence of node. IF it exists, do not add this command to the stage.
		node.setId(id);
		
		Platform.runLater(() -> 
		{
			Pane sp = (Pane)stage.getScene().getRoot();
			sp.getChildren().add(node);
			System.out.println(node.getId());
		});
		return "Set command execution completed successfully.";
	}	 
}
