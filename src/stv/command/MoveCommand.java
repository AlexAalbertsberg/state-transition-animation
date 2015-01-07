package stv.command;

import java.util.List;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stv.json.JSONObject;

public class MoveCommand extends AbsCommand {
	
	private String id;
	private int positionX;
	private int positionY;
	
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
		
		Pane sp = (Pane)stage.getScene().getRoot();
		List<Node> list = sp.getChildren();
		
		
		if(list != null && !list.isEmpty())
		{
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i).getId() != null && list.get(i).getId().equals(id))
				{
					Node n = list.get(i);
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
}
