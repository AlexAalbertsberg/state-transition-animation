package stv.command;

import java.util.List;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stv.json.JSONObject;

/**
 * Tells a GUI element to move somewhere.
 * @author Alex Aalbertsberg
 */
public class MoveCommand extends AbsCommand 
{
	
	private String id;
	private int positionX;
	private int positionY;
	
	/**
	 * Constructor
	 * @param json - Received JSONObject.
	 * @param stage - Server application's stage.
	 */
	public MoveCommand(JSONObject json, Stage stage) {
		super(json, stage);
	}

	@Override
	public String performCommand() {
		System.out.println("Move Command issued");
		
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
		
		// Get the stage's pane.
		Pane sp = (Pane)stage.getScene().getRoot();
		// Get the pane's children.
		List<Node> list = sp.getChildren();
		
		// check if an id was passed.
		if(id != null)
		{
			// Check whether nodes exist.
			if(list != null && !list.isEmpty())
			{
				// Loop through all nodes.
				for(int i = 0; i < list.size(); i++)
				{
					// Check for the received id.
					if(list.get(i).getId() != null && list.get(i).getId().equals(id))
					{
						// Node found!
						Node n = list.get(i);
						// Apply new X and Y coordinates to the node.
						// This needs to happen on the GUI thread, so we used runLater.
						Platform.runLater(() ->
						{
							n.setLayoutX(positionX);
							n.setLayoutY(positionY);
						});
					}
				}
				return "Move command executed successfully.";
			}
			else
			{
				return "List is null or empty.";
			}
		}
		else
		{
			return "No id was specified in the JSONObject.";
		}
	}
}
